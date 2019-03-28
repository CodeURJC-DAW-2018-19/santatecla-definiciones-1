import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment'
import { HeaderService } from '../header/header.service';

const URL = environment.baseUrl;

export interface User {
    id?: number;
    name: string;
    roles: string[];
    authdata: string;
}

@Injectable()
export class LoginService {

    isLogged = false;
    isTeacher = false;
    user: User;
    auth: string;

    constructor(private http: HttpClient, private headerService: HeaderService) {
        let user = JSON.parse(localStorage.getItem('currentUser'));
        if (user) {
            console.log('Logged user');
            this.setCurrentUser(user);
        }
    }

    logIn(user: string, pass: string) {

        let auth = window.btoa(user + ':' + pass);

        const headers = new HttpHeaders({
            Authorization: 'Basic ' + auth,
            'X-Requested-With': 'XMLHttpRequest',
        });

        return this.http.get<User>('/api/logIn', { headers })
            .pipe(map(user => {

                if (user) {
                    this.setCurrentUser(user);
                    user.authdata = auth;
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            }));
    }

    logOut() {

        return this.http.get(URL + '/logOut').pipe(
            map(response => {
                this.removeCurrentUser();
                return response;
            }),
        );
    }

    private setCurrentUser(user: User) {
        this.isLogged = true;
        this.user = user;
        this.isTeacher = this.user.roles.indexOf('ROLE_TEACHER') !== -1;
    }

    removeCurrentUser() {
        localStorage.removeItem('currentUser');
        this.isLogged = false;
        this.isTeacher = false;
        this.headerService.resetTabs();
    }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

/**
 * Service for teacher requests to backend
 */
@Injectable()
export class TeacherService {
  constructor(private http: HttpClient) { };
}
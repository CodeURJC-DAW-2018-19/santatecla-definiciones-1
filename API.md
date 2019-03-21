# API Rest
We divided the urls in different categories depending on the elements involved in them. Some urls are only accesible from the student view while others from the teacher. The resource API has GET, POST, PUT and DELETE methods. http: // localhost: 8080 followed by the containt request URL.
**API queries have been preceded by /api**

## Authentication

#### [](https://github.com/Caumel/DAW-G11-2018/blob/master/API.md#login)login

Allows a user to log in.

## Chapters
The following queries will be preceded by /chapters

#### Show chapters
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-1)URL
    
    < /chapters > or < / >
    
-   ##### []([](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-1))Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-1)URL Params:
    
    -   page=[int]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-1)Example of query:
    
    -   URL: 	`/api/chapters`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-1) Success Response:
	```
	{
	    "size": 10,
	    "number": 0,
	    "totalElements": 11,
	    "last": false,
	    "totalPages": 2,
	    "sort": {
	        "sorted": false,
	        "unsorted": true,
	        "empty": true
	    },
	    "first": true,
	    "numberOfElements": 10,
	    "content": [
	        {
	            "id": 4,
	            "chapterName": "Tema 1: Desarrollo web servidor"
	        },
	        {
	            "id": 5,
	            "chapterName": "Tema 2: Despliegue de webs"
	        },
	        {
	            "id": 6,
	            "chapterName": "Tema 3: Desarrollo web de cliente avanzado: SPA"
	        },
	        {
	            "id": 7,
	            "chapterName": "Tema 4"
	        },
	        {
	            "id": 8,
	            "chapterName": "Tema 5"
	        },
	        {
	            "id": 9,
	            "chapterName": "Tema 6"
	        },
	        {
	            "id": 10,
	            "chapterName": "Tema 7"
	        },
	        {
	            "id": 11,
	            "chapterName": "Tema 8"
	        },
	        {
	            "id": 12,
	            "chapterName": "Tema 9"
	        },
	        {
	            "id": 13,
	            "chapterName": "Tema 10"
	        }
	    ]
	}
	```

#### New chapter
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-2)URL
    
    < /chapters >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-2)Method:
    
    `POST`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-2)URL Params:

	`EMPTY`

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-2)Example of query:
    
    -   URL: `/api/chapters`
	
	-   Data Params:
		```
			{
				"chapterName":"Tema 12 prueba"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-2)Success Response:
	```
		{
			"id": 56,
			"chapterName": "Tema 12 prueba"
		}
	```

#### Erase chapter
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-3)URL
    
    < /chapters/{id} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-3)Method:
    
    `DELETE`
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-3)URL Params:

	- 	id=[long] 
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-3)Example of query:
    
    -   URL: `/api/chapters/56`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-3)Success Response:
	```
		{
		    "id": 56,
		    "chapterName": "Tema 12 prueba",
		    "concepts": []
		}
	```


## Concepts
The following queries will be preceded by /chapters/{id}/concepts

#### Show concepts
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-4)URL
    
    < /chapters/{id}/concepts >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-4)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-4)URL Params:
    
	- 	id=[long]
    -   page=[int]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-4)Example of query:
    
    -   URL:  `/api/chapters/4/concepts?page=0`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-4) Success Response:
	```
		{
			"size": 10,
			"number": 0,
			"totalElements": 11,
			"last": false,
			"totalPages": 2,
			"sort": {
				"sorted": false,
				"unsorted": true,
				"empty": true
			},
			"first": true,
			"numberOfElements": 10,
			"content": [
				{
					"id": 15,
					"conceptName": "Spring",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 16,
					"conceptName": "APIs REST",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 17,
					"conceptName": "Java EE",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 18,
					"conceptName": "Javascript",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 19,
					"conceptName": "HTML",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 20,
					"conceptName": "CSS",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 21,
					"conceptName": "Base de datos",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 22,
					"conceptName": "Adobe Flash",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 23,
					"conceptName": "TCP",
					"URL": "http://www.urldelconcepto.com"
				},
				{
					"id": 24,
					"conceptName": "Navegador",
					"URL": "http://www.urldelconcepto.com"
				}
    		]
		}
	```

#### New concept
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-5)URL
    
    < /chapters/{id}/concepts >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-5)Method:
    
    `POST`

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-5)URL Params:

	- 	id=[long] 


-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-5)Example of query:
    
    -   URL: `/api/chapters/4/concepts`
	-	Data Params:
		```
			{
				"conceptName": "concepto prueba",
				"URL": "http://www.urldelconcepto.com"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-5)Success Response:
	```
		{
			"id": 56,
			"conceptName": "concepto prueba",
			"URL": "http://www.urldelconcepto.com"
		}
	```

#### Modify concept
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-6)URL
    
    < /chapters/{id}/concepts/{conceptId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-6)Method:
    
    `PUT`

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-6)URL Params:

	- 	id=[long]
	- 	conceptId=[long] 
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-6)Example of query:
    
    -   URL:  `/api/chapters/4/concepts/16`
	-   Data Params:
		```
			{
				"conceptName": "concepto actualizado",
				"URL": "http://www.urldelconceptoactualizado.com"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-6)Success Response:
	```
		{
			"id": 16,
			"conceptName": "concepto actualizado",
			"URL": "http://www.urldelconceptoactualizado.com"
    	}
	```

#### Erase concept
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-7)URL
    
    < /chapters/{id}/concepts/{conceptId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-7)Method:
    
    `DELETE`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-7)URL Params:

	-	id=[long]
	- 	conceptId=[long]
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-7)Example of query:
    
    -   URL: `/api/chapters/4/concepts/56`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-7)Success Response:
	```
		{
			"id": 56,
			"conceptName": "concepto prueba",
			"URL": "http://www.urldelconcepto.com"
		}
	```


## Diagram
The following queries will be preceded by /diagraminfo

#### Show diagram
-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-8)URL
    
    < /diagraminfo >
    
-   ##### []([](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-8))Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-8)URL Params:
    
    -   `EMPTY`

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-8)Example of query:
    
    -   URL: `/api/diagraminfo`

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-8)Success response:
	```
		{
			"size": 0,
			"number": 0,
			"totalElements": 11,
			"last": true,
			"totalPages": 1,
			"sort": {
				"sorted": false,
				"unsorted": true,
				"empty": true
			},
			"first": true,
			"numberOfElements": 11,
			"content": [
				{
					"chapterName": "Tema 1: Desarrollo web servidor",
					"unmarked": 1,
					"correct": 9,
					"incorrect": 2
				},
				{
					"chapterName": "Tema 2: Despliegue de webs",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 3: Desarrollo web de cliente avanzado: SPA",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 4",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 5",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 6",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 7",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 8",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 9",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 10",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				},
				{
					"chapterName": "Tema 11",
					"unmarked": 0,
					"correct": 0,
					"incorrect": 0
				}
			]
		}
	```
		


## Answers
The following queries will be preceded by /concepts/{id}
#### Show marked answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-9)URL
    
    < /concepts/{id} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-9)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-9)URL Params:
    
	-	id=[long]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-9)Example of query:
    
    -   URL: `/api/concepts/15`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-9) Success Response:
	```
	{
		"id": 15,
		"conceptName": "Spring",
		"URL": "http://www.urldelconcepto.com"
	}
	```
#### Show marked answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-9)URL
    
    < /concepts/{id}/markedanswers >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-9)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-9)URL Params:
    
	-	id=[long]
    -   page=[int]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-9)Example of query:
    
    -   URL: `/api/concepts/15/markedanswers?page=0`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-9) Success Response:
	```
		{
			"size": 10,
			"number": 0,
			"totalElements": 11,
			"last": false,
			"totalPages": 2,
			"sort": {
				"sorted": false,
				"unsorted": true,
				"empty": true
			},
			"first": true,
			"numberOfElements": 10,
			"content": [
				{
					"id": 28,
					"answerText": "UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 29,
					"answerText": "UN FRAMEWORK DE DESARROLLO DE APLICACIONES WEB",
					"marked": true,
					"correct": false,
					"justifications": [
						{
							"id": 40,
							"justificationText": "SÓLO SE PUEDE UTILIZAR PARA DESARROLLAR SERVICIOS REST",
							"marked": true,
							"valid": false,
							"error": "SPRING PERMITE EL DESARROLLO DE DIVERSOS TIPOS DE APLICACIONES DE SERVIDOR: APLICACIONES WEB, SERVICIOS REST, ANÁLISIS DE DATOS BIG DATA..."
						}
					]
				},
				{
					"id": 31,
					"answerText": "UN FRAMEWORK COMERCIAL",
					"marked": true,
					"correct": false,
					"justifications": [
						{
							"id": 41,
							"justificationText": "ES UN FRAMEWORK DE SOFTWARE LIBRE",
							"marked": true,
							"valid": true,
							"error": null
						},
						{
							"id": 42,
							"justificationText": "ES UNA IMPLEMENTACIÓN DE JPA",
							"marked": false,
							"valid": false,
							"error": null
						}
					]
				},
				{
					"id": 32,
					"answerText": "UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR WEB COMO EN UN SERVIDOR DE APLICACIONES",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 33,
					"answerText": "UN FRAMEWORK BASADO EN JAVA EE",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 34,
					"answerText": "UN FRAMEWORK QUE PERMITE DESARROLLAR APLICACIONES DE SERVIDOR",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 35,
					"answerText": "UN FRAMEWORK PARA DESARROLLAR APLICACIONES WEB, SERVICIOS REST, WEBSOCKETS, ANÁLISIS DE BIG DATA...",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 36,
					"answerText": "UN FRAMEWORK QUE SOPORTA ACCESO A BASES DE DATOS TANTO RELACIONALES COMO NO RELACIONALES",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 37,
					"answerText": "UN FRAMEWORK QUE SOPORTA GROOVY",
					"marked": true,
					"correct": true,
					"justifications": []
				},
				{
					"id": 38,
					"answerText": "UN FRAMEWORK QUE SOPORTA REACTOR",
					"marked": true,
					"correct": true,
					"justifications": []
				}
			]
		}
	```

#### Show unmarked answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-10)URL
    
    < /concepts/{id}/unmarkedanswers >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-10)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-10)URL Params:
    
	-	id=[long]
    -   page=[int]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-10)Example of query:
    
    -   URL:  `/api/concepts/15/unmarkedanswers?page=0`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-10) Success Response:
	```
		{
			"size": 10,
			"number": 0,
			"totalElements": 1,
			"last": true,
			"totalPages": 1,
			"sort": {
				"sorted": false,
				"unsorted": true,
				"empty": true
			},
			"first": true,
			"numberOfElements": 1,
			"content": [
				{
					"id": 30,
					"answerText": "UN FRAMEWORK DE DESARROLLO DE APLICACIONES EMPRESARIALES BASADO EN JAVA",
					"marked": false,
					"correct": false,
					"justifications": []
				}
			]
		}
	```

#### New answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-11)URL
    
    < /concepts/{id}/answers >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-11)Method:
    
    `POST`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-11)Example of query:
    
    -   URL: `/api/concepts/15/answers/`
	-	Data Params:
		```
			{
				"answerText": "respuesta de prueba",
				"marked": true,
				"correct": true
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-11)Success Response:
	```
		{
			"id": 56,
			"answerText": "respuesta de prueba",
			"marked": true,
			"correct": true,
			"justifications": null
		}
	```

#### Modify answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-12)URL
    
    < /concepts/{id}/answers/{answerId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-12)Method:
    
    `PUT`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-12)URL Params:

	- 	id=[long]
	-	answerId=[long]
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-12)Example of query:
    
    -   URL: `/api/concepts/15/answers/56`
	-	Data Params:
		```
			{
				"answerText": "RESPUESTA ACTUALIZADA",
				"correct": false,
				"justText": "nueva justificacion",
				"valid": false,
				"errorText": "nuevo error"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-6)Success Response:
	```
		{
			"id": 56,
			"answerText": "RESPUESTA ACTUALIZADA",
			"marked": true,
			"correct": false,
			"justifications": [
				{
					"id": 56,
					"justificationText": "nueva justificacion",
					"marked": true,
					"valid": false,
					"error": "nuevo error"
				}
			]
		}
	```

#### Correct answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-13)URL
    
    < /concepts/{id}/correct/{answerId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-13)Method:
    
    `PUT`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-13)URL Params:

	- 	id=[long]
	-	answerId=[long]
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-13)Example of query:
    
    -   URL: `https://localhost:8443/api/concepts/15/correct/30`
	-	Data Params:
		```
			{
				"correct": false,
				"justificationTextNew": "nueva justificacion"
			}
		```

        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-13)Success Response:
	```
		{
			"id": 30,
			"answerText": "UN FRAMEWORK DE DESARROLLO DE APLICACIONES EMPRESARIALES BASADO EN JAVA",
			"marked": true,
			"correct": false,
			"justifications": [
				{
					"id": 56,
					"justificationText": "nueva justificacion",
					"marked": true,
					"valid": true,
					"error": null
				}
			]
		}
	```
#### Erase answer
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-14)URL
    
    < /concepts/{id}/answers/{answerId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-14)Method:
    
    `DELETE`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-14)URL Params:

	- 	id=[long]
	-	answerId=[long]
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-14)Example of query:
    
    -   URL: `/api/concepts/15/answers/56`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-14)Success Response:
	```
		{
			"id": 56,
			"answerText": "respuesta de prueba",
			"marked": true,
			"correct": true,
			"justifications": []
		}
	```

## Justifications
The following queries will be preceded by /answers/{answerId}

#### New justification
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-15)URL
    
    < /answers/{answerId}/justifications >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-15)Method:
    
    `POST`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-15)	URL params:
    
    - 	answerId=[long]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-15)Example of query:
    
    -   URL: `api/answers/29/justifications`
	-	Data Params:
		```
			{
				"id": 56,
				"justificationText": "justificacion añadida",
				"marked": true,
				"valid": true,
				"error": "no se vera"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-15) Success Response:
	```
		{
			"id": 56,
			"answerText": "respuesta de prueba",
			"marked": true,
			"correct": true,
			"justifications": []
		}
	```

#### Modify justification
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-16)URL
    
    < /answers/{answerId}/justifications/{justId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-16)Method:
    
    `PUT`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-16)URL Params:
    
	- 	answerId=[long]
	- 	justId=[long]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-16)Example of query:
    
    -   URL: `/api/answers/29/justifications/56`
	- 	Data Params: 
		```
			{
				"id": 56,
				"justificationText": "justificacion actualizada",
				"marked": true,
				"valid": false,
				"error": "texto de error"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-16) Success Response:
	```
		{
			"id": 56,
			"justificationText": "justificacion añadida",
			"marked": true,
			"valid": false,
			"error": "texto de error"
		}
	```

#### Correct justification
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-17)URL
    
    < /answers/{answerId}/correct/{justId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-17)Method:
    
    `PUT`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-17)URL Params:
    
	- 	answerId=[long]
	- 	justId=[long]

-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-17)Example of query:
    
    -   URL: `/api/answers/31/correct/56`
	- 	Data Params: 
		```
			{
				"valid": false,
				"errorText": "texto de error"
			}
		```
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-17) Success Response:
	```
		{
			"id": 56,
			"justificationText": "justificacion añadida",
			"marked": true,
			"valid": false,
			"error": "texto de error"
		}
	```

#### Erase justification
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-18)URL
    
    < /answers/{answerId}/justifications/{justId} >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-18)Method:
    
    `DELETE`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-18)URL Params:

	- 	answerId=[long]
	- 	justId=[long]
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-18)Example of query:
    
    -   URL: `/api/answers/29/justifications/56`
        
- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-18)Success Response:
	```
		{
			"id": 56,
			"answerText": "respuesta de prueba",
			"marked": true,
			"correct": true,
			"justifications": []
		}
	```

## Image
The following queries will be preceded by /concepts/{id}/image

#### New image

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-19)URL
    
    < /concepts/{id}/image >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-19)Method:
    
    `POST`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-19)URL Params:

	- 	id=[long]
	-	file=[MultipartFile]
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-19)Example of query:
    
    -   URL: `/api/concepts/15/image`
	-	Data params: `File: image file format (*.jpg, *.jpeg, *.png)`
        
#### Show image

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-20)URL
    
    < /concepts/{id}/image >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-20)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-20)URL Params:

	- 	id=[long]
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-20)Example of query:
    
    -   URL: `/api/concepts/15/image`

## User
The following queries are preceded by /api

#### New user

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-21)URL
    
    < /register >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-21)Method:
    
    `POST`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-21)URL Params:

	`EMPTY`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-21)Example of query:
    
    -   URL: `/api/register`
	-	Data params: 
		```
			{
				"id": 56,
				"name": "usertest",
				"password": "usertest",
				"roles": [
					"ROLE_STUDENT"
				]
			}
		```

- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-21)Success Response:

	```
		{
			"id": 56,
			"name": "usertest",
			"password": "$2a$10$fJkbzP2xv9gmTUtVKoBIe.VX79DirJMpy3Fd3PWVWJpSnVnouCQv.",
			"roles": [
				"ROLE_STUDENT"
			]
		}
	```
        
#### Show current user

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-22)URL
    
    < /currentuser >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-22)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-22)URL Params:

	`EMPTY`
    
	
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-22)Example of query:
    
    -   URL: `/api/currentuser`

- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-22)Success Response:

	```
		{
			"id": 56,
			"name": "usertest",
			"password": "$2a$10$fJkbzP2xv9gmTUtVKoBIe.VX79DirJMpy3Fd3PWVWJpSnVnouCQv.",
			"roles": [
				"ROLE_STUDENT"
			]
		}
	```

## Question
The following queries are preceded by /concepts/{id}

#### Show marked questions

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-23)URL
    
    < /concepts/{id}/markedquestions >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-23)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-23)URL Params:

	- 	id=[long]
	- 	page=[int]
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-23)Example of query:
    
    -   URL: `/api/concepts/15/markedquestions`

- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-23)Success Response:

	```
		{
			"size": 10,
			"number": 0,
			"totalElements": 11,
			"last": false,
			"totalPages": 2,
			"sort": {
				"sorted": false,
				"unsorted": true,
				"empty": true
			},
			"first": true,
			"numberOfElements": 10,
			"content": [
				{
					"id": 43,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 44,
					"questionText": "¿ES CIERTO QUE SPRING NO ES UN FRAMEWORK DE DESARROLLO DE APLICACIONES WEB PORQUE SÓLO SE PUEDE UTILIZAR PARA DESARROLLAR SERVICIOS REST?",
					"type": 3,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": false
				},
				{
					"id": 47,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 48,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 49,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 50,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 51,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 52,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 53,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				},
				{
					"id": 54,
					"questionText": "¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
					"type": 2,
					"yesNoQuestion": true,
					"userResponse": true,
					"marked": true,
					"correct": true
				}
			]
		}
	```

#### Show unmarked questions

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-24)URL
    
    < /concepts/{id}/unmarkedquestions >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-24)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-24)URL Params:

	- 	id=[long]
	- 	page=[int]
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-24)Example of query:
    
    -   URL: `/api/concepts/15/unmarkedquestions`

- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-24)Success Response:

	```
		{
			"size": 10,
			"number": 0,
			"totalElements": 2,
			"last": true,
			"totalPages": 1,
			"sort": {
				"sorted": false,
				"unsorted": true,
				"empty": true
			},
			"first": true,
			"numberOfElements": 2,
			"content": [
				{
					"id": 45,
					"questionText": "¿QUÉ ES SPRING?",
					"type": 0,
					"yesNoQuestion": false,
					"userResponse": false,
					"marked": false,
					"correct": false
				},
				{
					"id": 46,
					"questionText": "¿POR QUÉ NO ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK COMERCIAL?",
					"type": 1,
					"yesNoQuestion": false,
					"userResponse": false,
					"marked": false,
					"correct": false
				}
			]
		}
	```

#### Show new question

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-25)URL
    
    < /concepts/{id}/newquestion >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-25)Method:
    
    `GET`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-25)URL Params:

	- 	id=[long]
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-25)Example of query:
    
    -   URL: `/api/concepts/15/newquestion`

- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-25)Success Response:
	```
		{
			"id": 0,
			"questionText": "¿ES CIERTO QUE SPRING NO ES UN FRAMEWORK DE DESARROLLO DE APLICACIONES WEB PORQUE SÓLO SE PUEDE UTILIZAR PARA DESARROLLAR SERVICIOS REST?",
			"type": 3,
			"yesNoQuestion": true,
			"userResponse": false,
			"marked": false,
			"correct": false
		}
	```

#### Save answer

-	##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#url-26)URL
    
    < /concepts/{id}/saveanswer >
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#method-26)Method:
    
    `POST`
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#params-26)URL Params:

	- 	id=[long]
    
-   ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#example-26)Example of query:
    
    -   URL: `/api/concepts/15/saveanswer`
	-	Data params:
		```
			{
				"answerText": "texto de respues",
				"answerOption": "",
				"questionText": "texto de pregunta",
				"answerId": 56,
				"justificationQuestionId": 0,
				"questionType": 0
			}
		```

- ##### [](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/tree/master/API.md#success-26)Success Response:
	```
		{
			"id": 56,
			"answerText": "TEXTO DE RESPUES",
			"marked": false
		}
	```

import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Libro } from '../models/libro.model';

@Injectable({ providedIn: 'root' })
export class LibroService {

  private apiUrl = 'http://localhost:8081/api/libros';

  constructor(private http: HttpClient) {}

  listarLibros(
    page = 0,
    size = 10,
    sort = 'id',
    direction = 'ASC',
    campo?: string,
    operador?: string,
    valor?: string
  ) {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sort)
      .set('direction', direction);

    if (campo && operador && valor) {
      params = params
        .set('campo', campo)
        .set('operador', operador)
        .set('valor', valor);
    }

    return this.http.get<any>(this.apiUrl, { params });
  }

  crearLibro(libro: Libro) {
    return this.http.post(this.apiUrl, libro);
  }

  actualizarLibro(id: number, libro: Libro) {
    return this.http.put(`${this.apiUrl}/${id}`, libro);
  }

  eliminarLibro(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
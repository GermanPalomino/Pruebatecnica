import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { LibroService } from '../../app/services/libro.service';
import { Libro } from '../../app/models/libro.model';

@Component({
  selector: 'app-libros',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './libros.component.html'
})
export class LibrosComponent implements OnInit {

  libros: Libro[] = [];

  // paginación / orden
  page = 0;
  size = 10;
  sort = 'id';
  direction = 'ASC';

  // filtros server side
  campo = 'nombre';
  operador = 'contiene';
  valor = '';

  libroEditando?: Libro;
  mensajeSuccess = '';
  mensajeError = '';

  form!: FormGroup; // 👈 SE DECLARA, NO SE INICIALIZA AQUÍ

  constructor(
    private fb: FormBuilder,
    private libroService: LibroService
  ) {}

  ngOnInit(): void {
    // 👇 AQUÍ SÍ EXISTE fb
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      autor: ['', Validators.required],
      descripcion: [''],
      fechaPublicacion: ['', Validators.required],
      numeroEjemplares: [1, Validators.required],
      costo: [0, Validators.required]
    });

    this.cargarLibros();
  }

  cargarLibros(): void {
    this.libroService
      .listarLibros(
        this.page,
        this.size,
        this.sort,
        this.direction,
        this.campo,
        this.operador,
        this.valor
      )
      .subscribe({
        next: res => this.libros = res.content,
        error: err =>
          this.mensajeError = err.error?.message || 'Error al cargar libros'
      });
  }

  guardar(): void {
    if (this.form.invalid) return;

    const libro = this.form.value as Libro;

    const request = this.libroEditando
      ? this.libroService.actualizarLibro(this.libroEditando.id!, libro)
      : this.libroService.crearLibro(libro);

    request.subscribe({
      next: () => {
        this.mensajeSuccess = this.libroEditando
          ? 'Libro actualizado correctamente'
          : 'Libro creado correctamente';

        this.form.reset();
        this.libroEditando = undefined;
        this.cargarLibros();
      },
      error: err =>
        this.mensajeError = err.error?.message || 'Error al guardar'
    });
  }

  editar(libro: Libro): void {
    this.libroEditando = libro;
    this.form.patchValue(libro);
  }

  cancelarEdicion(): void {
    this.libroEditando = undefined;
    this.form.reset();
  }

  eliminar(id: number): void {
    if (!confirm('¿Eliminar el libro?')) return;

    this.libroService.eliminarLibro(id).subscribe({
      next: () => this.cargarLibros(),
      error: err =>
        this.mensajeError = err.error?.message || 'Error al eliminar'
    });
  }

  cambiarOrden(campo: string): void {
    this.sort = campo;
    this.direction = this.direction === 'ASC' ? 'DESC' : 'ASC';
    this.cargarLibros();
  }
}
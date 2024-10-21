import tkinter as tk
from tkinter import messagebox

class UsuariosApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Ejercicio 12: Añadir y eliminar usuarios")
        self.root.geometry("600x800")

        # Lista de usuarios
        self.lista_usuarios = []

        # Frame superior
        self.frame_top = tk.Frame(root)
        self.frame_top.grid(row=0, padx=5, pady=5, sticky="nsew")

        # Campo para el nombre de usuario
        tk.Label(self.frame_top, text="Nombre de usuario:").grid(row=0, column=0, sticky="w")
        self.entrada_nombre = tk.Entry(self.frame_top, width=30)
        self.entrada_nombre.grid(row=0, column=1)

        # Scale para seleccionar la edad
        tk.Label(self.frame_top, text="Edad de usuario:").grid(row=1, column=0, sticky="w")
        self.scale = tk.Scale(self.frame_top, from_=0, to=100, orient="horizontal")
        self.scale.grid(row=1, column=1, pady=20)

        # Radiobuttons para el género
        tk.Label(self.frame_top, text="Género de usuario:").grid(row=2, column=0, sticky="w")
        self.var_radio = tk.StringVar(value="masculino")
        tk.Radiobutton(self.frame_top, text="masculino", variable=self.var_radio, value="masculino").grid(row=2, column=1, sticky="w")
        tk.Radiobutton(self.frame_top, text="femenino", variable=self.var_radio, value="femenino").grid(row=3, column=1, sticky="w")
        tk.Radiobutton(self.frame_top, text="otro", variable=self.var_radio, value="otro").grid(row=4, column=1, sticky="w")

        # Botón para añadir el nuevo usuario
        tk.Button(self.frame_top, text="Añadir usuario", command=self.clickar_anadir_usuario).grid(row=5, columnspan=2, pady=10)

        # Frame central
        self.frame_central = tk.Frame(root)
        self.frame_central.grid(row=1, padx=5, pady=5, sticky="nsew")

        # Configuración de expansión del grid
        root.grid_rowconfigure(1, weight=1)  # Permite que el frame central se expanda
        root.grid_columnconfigure(0, weight=1)  # Permite que el frame central ocupe todo el ancho

        # Etiqueta lista de usuarios
        tk.Label(self.frame_central, text="Lista de usuarios:").grid(row=0, column=0, columnspan=2)

        # Listbox y scrollbar
        self.listbox = tk.Listbox(self.frame_central, selectmode=tk.SINGLE)
        self.listbox.grid(row=1, column=0, sticky="nsew")

        self.scrollbar = tk.Scrollbar(self.frame_central)
        self.scrollbar.grid(row=1, column=1, sticky="ns")

        self.listbox.config(yscrollcommand=self.scrollbar.set)
        self.scrollbar.config(command=self.listbox.yview)

        # Botón para eliminar un usuario de la lista
        tk.Button(self.frame_central, text="Eliminar usuario", command=self.eliminar_usuario).grid(row=2, column=0, pady=10, columnspan=2)

        # Frame inferior
        self.frame_bottom = tk.Frame(root)
        self.frame_bottom.grid(row=2, padx=5, pady=5, sticky="nsew")

        # Botón de salir
        tk.Button(self.frame_bottom, text="Salir", command=self.salir).pack(side=tk.BOTTOM, padx=5)

        # Menú
        self.menu_bar = tk.Menu(root)
        root.config(menu=self.menu_bar)

        archivo_menu = tk.Menu(self.menu_bar, tearoff=0)
        archivo_menu.add_command(label="Guardar Lista", command=self.guardar_lista)
        archivo_menu.add_command(label="Cargar Lista", command=self.cargar_lista)
        self.menu_bar.add_cascade(label="Archivo", menu=archivo_menu)

    # Métodos
    def clickar_anadir_usuario(self):
        nombre = self.entrada_nombre.get()
        edad = self.scale.get()
        genero = self.var_radio.get()

        usuario = f"Nombre: {nombre}, Edad: {edad}, Género: {genero}"
        self.lista_usuarios.append(usuario)
        self.actualizar_listbox()
        self.entrada_nombre.delete(0, tk.END)
        self.scale.set(0)
        self.var_radio.set("masculino")

    def actualizar_listbox(self):
        self.listbox.delete(0, tk.END)
        for usuario in self.lista_usuarios:
            self.listbox.insert(tk.END, usuario)

    def eliminar_usuario(self):
        usuario_seleccionado = self.listbox.curselection()
        if usuario_seleccionado:
            indice = usuario_seleccionado[0]
            del self.lista_usuarios[indice]
            self.actualizar_listbox()
        else:
            messagebox.showwarning("AVISO", "Debes seleccionar un usuario.")

    def salir(self):
        self.root.quit()

    def guardar_lista(self):
        messagebox.showinfo("Guardar Lista", "La lista de usuarios ha sido guardada.")

    def cargar_lista(self):
        messagebox.showinfo("Cargar Lista", "La lista de usuarios ha sido cargada.")

if __name__ == "__main__":
    root = tk.Tk()
    app = UsuariosApp(root)
    root.mainloop()

import tkinter as tk
from modeloNotas import ModeloNotas
from vistaNotas import VistaNotas
from controladorNotas import ControladorNotas

if __name__ == "__main__":
    root = tk.Tk()
    modeloNotas = ModeloNotas()
    vistaNotas = VistaNotas(root)
    controladorNotas = ControladorNotas(vistaNotas, modeloNotas)
    root.mainloop()


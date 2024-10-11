from Heroe import Heroe
from Mazmorra import Mazmorra

# desde el main se ejecuta el programa
def main():
    nombre_heroe = input("Introduce el nombre_heroe de tu héroe: ") # pedimos al usuario que introduzca por teclado
                                                                    #e l nombre que ha elegido para el héroe
    heroe = Heroe(nombre_heroe) # Para instanciar el heroe, que recibe por parámetro un nombre, le pasamos el que
                                # acaba de introducir el usuario

    mazmorra = Mazmorra (heroe) # También instanciamos una mazmorra, a la que le pasamos el heroe recién creado
                                # Ya tenemos lo necesario, porque el monstruo y el tesoro se inicializan en la mazmorra
    mazmorra.jugar() # llamamos al metodo jugar de la mazmorra, que es el metodo que inicia el juego

if __name__ == "__main__":
    main()
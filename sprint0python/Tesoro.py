import random # importamos la clase random para poder usar el metodo choice

# La clase Tesoro representa los tesoros que el héroe puede encontrar después de derrotar a un monstruo.
# Los tesoros pueden mejorar las habilidades del héroe o restaurar su salud.

class Tesoro:

    # Constructor de la clase Tesoro
    def __init__(self):
        # Atributo de la clase: beneficios: lista de los tesoros existentes y su valor
        self.beneficios = [("aumento de ataque", 5),("aumento de defensa", 2),("restauración de salud", 20)]

    # Metodo de la clase, recibe por parámetro el héroe (es una clase que no afecta al monstruo)
    def encontrar_tesoro(self, heroe):
        beneficio, valor = random.choice(self.beneficios) # random.choice elije de forma aleatoria uno de los beneficios
                                                        # que se han listado en el constructor del metodo
        print(f"Héroe ha encontrado un tesoro: {beneficio}.")  # muestra por pantalla el beneficio (tesoro) encontrado

        # Dependiendo del beneficio, el atributo correspondiente del héroe, variará según lo indicado en el constructor
        if beneficio == "aumento de ataque":
            heroe.ataque += 5  # si beneficio: aumento de ataque: el ataque del héroe aumenta en 5 puntos
            print(f"El ataque de {heroe.nombre_heroe} aumenta a {heroe.ataque}.")
        elif beneficio == "aumento de defensa":
            heroe.defensa += 2  # si beneficio: aumento de defensa: la defensa del héroe aumenta en 2 puntos
            print(f"La defensa de {heroe.nombre_heroe} aumenta a {heroe.defensa}.")
        elif beneficio == "restauración de salud":
            heroe.salud += 20 # si beneficio: restauración de salud: la salud del héroe aumenta en 20 puntos
            if heroe.salud > heroe.salud_maxima: # control para que la salud nunca aumente la salud máxima
                heroe.salud = heroe.salud_maxima # en caso de que aumente, se iguala a salud maxima (100 puntos)
            print(f"La salud de {heroe.nombre_heroe} ha sido restaurada a {heroe.salud}.")

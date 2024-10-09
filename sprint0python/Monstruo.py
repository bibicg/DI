# La clase Monstruo representa a los enemigos a los que el héroe se enfrenta en la mazmorra

class Monstruo:

    # Constructor de la clase Monstruo.
    # Debe recibir todos los atributos por parámetro a la hora de construirlo (instanciarlo)
    def __init__(self, nombre, ataque, defensa, salud):
        # Atributos de la clase monstruo
        # Le daremos valor a la hora de crear al monstruo
        self.nombre = nombre
        self.ataque = ataque # Será un valor numérico
        self.defensa = defensa # Será un valor numérico
        self.salud = salud  # Salud inicial del monstruo

    # MÉTODOS DE LA CLASE MONSTRUO:

    # Atacar recibe por parámetros el héroe al que se enfrenta
    def atacar(self, heroe): # Mismo metodo que para el héroe
        print(f"El monstruo {self.nombre} ataca a {heroe.nombre_heroe}.") # Información para el jugador
        danho = self.ataque - heroe.defensa # El daño será el resultado del ataque del monstruo menos la defensa del héroe

        # Si el héroe sufre algún daño, se restará dicho daño de su salud
        # Se muestra por pantalla el daño recibido por el héroe, no la salud restante del mismo
        if danho > 0:
            heroe.salud -= danho
            print(f"El héroe {heroe.nombre_heroe} ha recibido {danho} puntos de daño.")
        else:
            print("El héroe ha bloqueado el ataque.")

    def esta_vivo(self): # Mismo metodo que para el héroe
        return self.salud > 0 # Devuelve un booleano: true si la salud es >0 (monstruo vivo) y false si salud<0 (monstruo muerto)

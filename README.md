# Ud4_Ejemplo2
_Ejemplo 2 de la Unidad 4._
Vamos a realizar View Recycling haciendo uso de ListView y ArrayAdapter para mostrar una lista con algunos de los vehículos (coches, naves, etc.) 
más famosos de películas y series. Se mostrará su nombre y la película o serie en la que apareció.
Además al pursar el elemento aparecerá un mensaje por pantalla con esos datos.

Para ello vamos a seguir los siguientes pasos:

## Paso 1: Creación del _layout_

Primero vamos a crear la lista usando _ListView_ en el fichero _activity_main.xml_:
```
<ListView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    android:id="@+id/lista"
    tools:context=".MainActivity">

</ListView>
```
Despúes necesitamos crear otro _layout_ ya que la lista va a contener dos _TextView_, uno para el nombre y otro para indicar dónde
apareció, cada uno con su _id_.

_elemento_lista.xml_:
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/nombreTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        tools:text="nombre"/>

    <TextView
        android:id="@+id/aparicionTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        tools:text="aparición"/>

</LinearLayout>
```
Observad que se utiliza el atributo _text_ del espacio de nombres _tools_ para ver cómo queda el diseño.

## Paso 2: Creación de la clase _Vehiculo_
Como cada elemento de la lista va a contener más de un dato relacionado con el vehículo, vamos a crear una clase con dos atributos, 
uno para el nombre y otro para la aparición.

_Vehiculo.java_:
```
public class Vehiculo {
    private String nombre; // Nombre del vehículo.
    private String aparicion; // Serie o película donde aparece.

    public Vehiculo(String nombre, String aparicion) {
        this.nombre = nombre;
        this.aparicion = aparicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAparicion() {
        return aparicion;
    }
}
```


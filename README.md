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

## Paso 3: Creación del adaptador _VehiculoAdapter_
El siguiente paso es crear el adaptador que le pasará las _Views_ a _ListView_. Éste heredará de la clase ArrayAdapter de tipo Vehiculo y deberá sobreescribir el método _getView_ que será el encargado de devolver la siguiente _View_.

_Vehiculoadapter.java_:
```
public class VehiculoAdapter extends ArrayAdapter<Vehiculo> {

    public VehiculoAdapter(Context context, ArrayList<Vehiculo> vehiculos) {
        super(context, 0, vehiculos);
    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {

        // Comprobamos si la View está siendo reutilizada, sino creamos la View
        View elemListaView = convertView;

        if(elemListaView == null)
            elemListaView = LayoutInflater.from(getContext()).inflate(R.layout.elementos_lista, parent, false);

        // Obtenemos el objeto que está en esta posición en la lista
        Vehiculo vechiculoActual = getItem(position);

        // Buscamos el TextView para el nombre y le asignamos el nombre del vechículo que está en la posición actual de la lista.
        TextView nombre = elemListaView.findViewById(R.id.nombreTextView);

        nombre.setText(vechiculoActual.getNombre());

        // Lo mismo para la aparición.
        TextView aparicion = elemListaView.findViewById(R.id.aparicionTextView);

        aparicion.setText(vechiculoActual.getAparicion());

        // Devolvemos la lista de los elementos con los dos nuevos TextView para qu ela muestre el ListView.
        return elemListaView;
    }
}
```
Al sobreescribir el método, nos aparece las notaciones _@androidx.annotation.NonNull_ y _@androidx.annotation.Nullable_ y puede que nos de un error si la dependencia de _@androidx.annotation_ no está en el fichero _build.gradle(Module:app)_.
Habrá que comprobarlo y sino está incorporarla:
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation "androidx.annotation:annotation:1.0.0"
    ...
}
```
Una vez añadida deberemos sincronizar el proyecto. _AndroidStudio_ nos avisará de ello y deberemos pulsar sobre el enlace _Sync Now_ que nos aparecerá en la parte superior de la pantalla del editor.

## Paso 4: Implementación de la clase _MainActivity_
El último paso es implementar la clase _MainActivity_ donde incluimos el _ArrayList_ de tipo _Vehiculo_ y añadimos sus elementos, creamos el _adapter_ a partir del _ArrayList_, buscamos la lista y le asignamos el _adapter_ y por último le asociamos un _OnClickItemListener_ para saber cuándo se ha pulsado en un elemento de la lista. En él obtenemos el vehículo pulsado y lo
mostramos por pantalla.

_MainActivity.java_:
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos el array de vehiculos.
        final ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        vehiculos.add(new Vehiculo("Ecto1", "Los cazafantasmas"));
        vehiculos.add(new Vehiculo("DeLorean", "Regreso al futuro"));
        vehiculos.add(new Vehiculo("Kitt", "El coche fantástico"));
        vehiculos.add(new Vehiculo("Halcón Milenario", "Star Wars"));
        vehiculos.add(new Vehiculo("Planet Express", "Futurama"));
        vehiculos.add(new Vehiculo("TARDIS", "Doctor Who"));
        vehiculos.add(new Vehiculo("USS Enterprise", "Star Trek"));
        vehiculos.add(new Vehiculo("Nabucodonosor", "Matrix"));
        vehiculos.add(new Vehiculo("Odiseus", "Ulises 31"));
        vehiculos.add(new Vehiculo("Nostromo", "Alien"));

        // Creamos el Adapter de vehículos a partir del arrayList.
        VehiculoAdapter adapter = new VehiculoAdapter(this, vehiculos);

        // Buscamos la lista y le pasamos los elementos que obtiene el adapter en el método getView
        ListView lista = findViewById(R.id.lista);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Al utilizar el ArrayList vehiculos en la inner class debemos declararlo final
                Vehiculo vehiculo = vehiculos.get(position);
                Toast.makeText(MainActivity.this, vehiculo.getNombre() + "\n" + vehiculo.getAparicion(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```
Observad que al hacer uso de una _Inner class_ el _ArrayList_ _vehiculos_ debe declararse _final_ o como un atributo de la clase _MainActivity_. En este ejemplo se ha declarado _final_.

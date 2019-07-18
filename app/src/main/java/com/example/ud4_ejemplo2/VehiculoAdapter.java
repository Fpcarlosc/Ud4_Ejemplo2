package com.example.ud4_ejemplo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

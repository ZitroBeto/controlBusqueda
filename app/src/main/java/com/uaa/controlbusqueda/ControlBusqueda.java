package com.uaa.controlbusqueda;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 02/10/2015.
 */
public class ControlBusqueda extends LinearLayout{
    private EditText txtBuscar;
    private ListView lstViewTextos;
    private  Context contexto;
    private String[] textos = new String[]{"hola", "roberto", "hizo", "esto", "Android", "android", "perro", "cosa", "hacer", "reloj", "Sony", "Apple", "Rana", "ala", "calculadora", ".Net", "C#", "#HashTag"};

    public ControlBusqueda(Context context) {
        super(context);
        this.contexto = context;
        this.init();
    }

    public ControlBusqueda(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.contexto = context;
        this.init();
    }

    public void setTextos(String[] txts){
        this.textos = txts;
    }

    //esto necesita API 11
    /*public ControlBusqueda(Context context, AttributeSet attrs, int defStyleAttr, String[] textos) {
        super(context, attrs, defStyleAttr);
        this.textos = textos;
    }*/

    //esto requiere API 21
    /*public ControlBusqueda(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, String[] textos) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.textos = textos;
    }*/

    private void init(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.busqueda_layout,this,true);

        txtBuscar = (EditText) findViewById(R.id.txtBuscar);
        lstViewTextos = (ListView) findViewById(R.id.lstViewTextos);

        this.resetListView(this.textos);

        this.txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String cadenaTotal = (txtBuscar.getText().toString());
                String[] coincidencias = getCoincidencias(textos, cadenaTotal);
                if (coincidencias.length > 0) { //se encontró algo -> mostrarlo
                    resetListView(coincidencias);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String[] getCoincidencias(String[] mainConjunto, String objetivo){
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<mainConjunto.length;i++){
            if(mainConjunto[i].toUpperCase().contains(objetivo.toUpperCase())){
                result.add(mainConjunto[i]);
            }
        }
        String[] finalRes = new String[result.size()];
        for(int i=0;i<result.size();i++){
            finalRes[i] = result.get(i);
        }
        return finalRes;
    }


    private void resetListView(String [] textos){
        ListAdapter adaptadorTexto =  new ArrayAdapter<String>(this.contexto,android.R.layout.simple_list_item_1, textos);
        this.lstViewTextos.setAdapter(adaptadorTexto);
    }
}


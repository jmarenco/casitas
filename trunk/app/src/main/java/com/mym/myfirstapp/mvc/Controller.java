package com.mym.myfirstapp.mvc;

import com.mym.myfirstapp.MainActivity;
import com.mym.myfirstapp.negocio.Casita;
import com.mym.myfirstapp.negocio.Empresa;
import com.mym.myfirstapp.negocio.Nivel;
import com.mym.myfirstapp.negocio.NivelFactory;

public class Controller
{
    // Activity principal
    private MainActivity _activity;

    // Nivel actual
    private Nivel _nivel;

    // Vista
    private Vista _vista;

    // Empresa seleccionada
    private Empresa _seleccionada = null;

    // Punto del último evento
    private float _lastx;
    private float _lasty;

    // Longitud de la línea
    private int _largo;

    // Constructor
    public Controller(MainActivity activity)
    {
        _activity = activity;
        _nivel = NivelFactory.getNivel1();
    }

    // Getters
    public MainActivity getActivity()
    {
        return _activity;
    }
    public Nivel getNivel()
    {
        return _nivel;
    }

    // Setter
    public void setVista(Vista vista)
    {
        _vista = vista;
    }

    // Acciones
    public void inicioDrag(float x, float y)
    {
        _seleccionada = _vista.empresaSeleccionada(x,y);
        _lastx = _seleccionada != null ? x : 0;
        _lasty = _seleccionada != null ? y : 0;
        _largo = 0;
    }
    public void drag(float x, float y)
    {
        if( _seleccionada != null && (_largo < 20 || _vista.segmentoLibre(_lastx, _lasty, x, y)) )
        {
            _vista.dibujar(_lastx, _lasty, x, y);
            _lastx = x;
            _lasty = y;
            _largo += 1;
        }
    }
    public void finDrag(float x, float y)
    {
        if( _seleccionada != null )
        {
            if( _vista.segmentoLibre(_lastx, _lasty, x, y) )
                _vista.dibujar(_lastx, _lasty, x, y);

            asignarServicio(x, y);

            if( _nivel.terminado() )
                _vista.terminarNivel();
        }

        _seleccionada = null;
        _lastx = 0;
        _lasty = 0;
        _largo = 0;
    }

    // Si hay una casa en la ubicación actual, le asigna el servicio de la empresa seleccionada
    private void asignarServicio(float x, float y)
    {
        Casita casita = _vista.casitaSeleccionada(x, y);

        if( casita != null && casita.getNecesidad(_seleccionada.getTipo()) == true )
        {
            casita.setServicio(_seleccionada.getTipo());
            _vista.dibujarServicio(casita, _seleccionada);
        }
    }
}

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
        _seleccionada = _nivel.getEmpresa(_vista.pointX(x), _vista.pointY(y));
        _lastx = _seleccionada != null ? x : 0;
        _lasty = _seleccionada != null ? y : 0;
    }
    public void drag(float x, float y)
    {
        if( _seleccionada != null )
        {
            _vista.dibujar(_lastx, _lasty, x, y);
            _lastx = x;
            _lasty = y;
        }
    }
    public void finDrag(float x, float y)
    {
        if( _seleccionada != null )
        {
            _vista.dibujar(_lastx, _lasty, x, y);
            asignarServicio(x, y);
        }

        _seleccionada = null;
        _lastx = 0;
        _lasty = 0;
    }

    // Si hay una casa en la ubicación actual, le asigna el servicio de la empresa seleccionada
    private void asignarServicio(float x, float y)
    {
        Casita casita = _nivel.getCasita(_vista.pointX(x), _vista.pointY(y));

        if( casita != null && casita.getNecesidad(_seleccionada.getTipo()) == true )
        {
            casita.setServicio(_seleccionada.getTipo());
            _vista.dibujarServicio(casita, _seleccionada);
        }
    }
}

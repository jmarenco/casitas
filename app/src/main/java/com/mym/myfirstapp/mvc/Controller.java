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

    // Número de nivel actual
    private static int _numeroNivel = 1;

    // Nivel actual
    private Nivel _nivel;

    // Vista y sonido
    private Vista _vista;
    private Sound _sound;

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
        _nivel = NivelFactory.getNivel(_numeroNivel);
        _sound = new Sound(_activity);
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

        _vista.copiarBitmap();
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

            verificarConexion(x, y);

            if( _nivel.terminado() )
                terminarNivel();
        }

        _seleccionada = null;
        _lastx = 0;
        _lasty = 0;
        _largo = 0;
    }

    // Si hay una casa en la ubicación actual, le asigna el servicio de la empresa seleccionada
    private boolean verificarConexion(float x, float y)
    {
        boolean ret = false;
        Casita casita = _vista.casitaSeleccionada(x, y);

        if( casita != null && casita.getNecesidad(_seleccionada.getTipo()) == true )
        {
            casita.setServicio(_seleccionada.getTipo());
            _vista.dibujarServicio(casita, _seleccionada);

            if( _nivel.terminado() == false )
                _sound.asignarServicio();

            ret = true;
        }

        // Si no había una casa, deshace el último cableado
        if( ret == false )
        {
            _vista.restaurarBitmap();
            _sound.advertirError();
        }

        return ret;
    }

    // Finalización exitosa del nivel
    private void terminarNivel()
    {
        _vista.terminarNivel();
        _sound.felicitar();
    }

    // Cambio de nivel
    public void nivelAnterior()
    {
        if( _numeroNivel > 1 )
            _numeroNivel -= 1;

        _activity.nuevoNivel();
    }
    public void nivelSiguiente()
    {
        if( _numeroNivel < NivelFactory.maxNiveles() )
            _numeroNivel += 1;

        _activity.nuevoNivel();
    }
    public void reiniciar()
    {
        _activity.nuevoNivel();
    }
}

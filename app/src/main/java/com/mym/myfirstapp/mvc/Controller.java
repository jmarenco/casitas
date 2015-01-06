package com.mym.myfirstapp.mvc;

import com.mym.myfirstapp.MainActivity;
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
}

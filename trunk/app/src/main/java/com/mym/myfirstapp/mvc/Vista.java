package com.mym.myfirstapp.mvc;

import com.mym.myfirstapp.MainActivity;
import com.mym.myfirstapp.R;
import com.mym.myfirstapp.negocio.Casita;
import com.mym.myfirstapp.negocio.Empresa;
import com.mym.myfirstapp.negocio.Nivel;

public class Vista
{
    // Activity principal y controller
    private MainActivity _activity;
    private Controller _controller;

    // Nivel actual
    private Nivel _nivel;

    // Constructor
    public Vista(Controller controller)
    {
        _controller = controller;
        _controller.setVista(this);

        _activity = _controller.getActivity();
        _nivel = _controller.getNivel();
    }

    // Inicializa el nivel
    public void inicializar()
    {
        for(Empresa empresa: _nivel.getEmpresas())
            _activity.crearImagen(R.drawable.industria60, empresa.getX(), empresa.getY());

        for(Casita casita: _nivel.getCasitas())
            _activity.crearImagen(R.drawable.casita, casita.getX(), casita.getY());
    }
}

package com.mym.myfirstapp.mvc;

import com.mym.myfirstapp.MainActivity;
import com.mym.myfirstapp.R;
import com.mym.myfirstapp.negocio.Casita;
import com.mym.myfirstapp.negocio.Empresa;
import com.mym.myfirstapp.negocio.Nivel;
import com.mym.myfirstapp.negocio.Objeto;

public class Vista
{
    // Activity principal y controller
    private MainActivity _activity;
    private Controller _controller;

    // Ancho y altura del display y del nivel
    private double _anchoDisplay;
    private double _alturaDisplay;
    private double _anchoNivel;
    private double _alturaNivel;

    // Nivel actual
    private Nivel _nivel;

    // Constructor
    public Vista(Controller controller)
    {
        _controller = controller;
        _controller.setVista(this);

        _activity = _controller.getActivity();
        _nivel = _controller.getNivel();

        _anchoDisplay = _activity.getAnchoDisplay();
        _alturaDisplay = _activity.getAlturaDisplay();
        _anchoNivel = _nivel.getAncho();
        _alturaNivel = _nivel.getAltura();
    }

    // Inicializa el nivel
    public void inicializar()
    {
        for(Empresa empresa: _nivel.getEmpresas())
            _activity.crearImagen(R.drawable.industria60, convX(empresa), convY(empresa));

        for(Casita casita: _nivel.getCasitas())
            _activity.crearImagen(R.drawable.casita, convX(casita), convY(casita));
    }

    // Convierte unidades del modelo a unidades en el display
    public int convX(Objeto objeto)
    {
        return (int)(objeto.getX() * _anchoDisplay / _anchoNivel);
    }
    public int convY(Objeto objeto)
    {
        return (int)(objeto.getY() * _alturaDisplay / _alturaNivel);
    }
}

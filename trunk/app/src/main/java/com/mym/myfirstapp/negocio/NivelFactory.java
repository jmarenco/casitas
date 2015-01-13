package com.mym.myfirstapp.negocio;

public class NivelFactory
{
    public static Nivel getNivel1()
    {
        Nivel ret = new Nivel(1);

        ret.addEmpresa(new Empresa(Empresa.Tipo.Luz, 20, 20));
        ret.addEmpresa(new Empresa(Empresa.Tipo.Gas, 200, 90));
        ret.addEmpresa(new Empresa(Empresa.Tipo.Telefono, 170, 20));
        ret.addCasita(construirCasita(60, 70, "LG"));
        ret.addCasita(construirCasita(160, 170, "G"));
        ret.addCasita(construirCasita(50, 240, "LGT"));
        ret.addCasita(construirCasita(150, 240, "LT"));
        ret.addCasita(construirCasita(20, 170, "T"));

        return ret;
    }

    // Construye una casita
    private static Casita construirCasita(double x, double y, String servicios)
    {
        Casita ret = new Casita(x, y);

        if( servicios.contains("L") )
            ret.setNecesidad(Empresa.Tipo.Luz);

        if( servicios.contains("G") )
            ret.setNecesidad(Empresa.Tipo.Gas);

        if( servicios.contains("T") )
            ret.setNecesidad(Empresa.Tipo.Telefono);

        return ret;
    }
}
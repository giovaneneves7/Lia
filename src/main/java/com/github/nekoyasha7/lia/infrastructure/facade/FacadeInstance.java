package com.github.nekoyasha7.lia.infrastructure.facade;

public class FacadeInstance
{
    private static IFacade facadeInstance;
    private static final Object MONITOR = new Object();

    public FacadeInstance()
    {
        super();
    }

    public static IFacade getInstance()
    {

        synchronized (MONITOR)
        {

            if(facadeInstance == null)
            {
                facadeInstance = new Facade();
            }

        }

        return facadeInstance;

    }
}

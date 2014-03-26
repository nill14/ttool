package com.github.nill14.ttool.mbean;

import java.io.Serializable;

//@ManagedBean
//@SessionScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = -9040217024800473940L;

	private String name;
    private String password;


    public String getName ()
    {
        return name;
    }


    public void setName (final String name)
    {
        this.name = name;
    }


    public String getPassword ()
    {
        return password;
    }


    public void setPassword (final String password)
    {
        this.password = password;
    }
}

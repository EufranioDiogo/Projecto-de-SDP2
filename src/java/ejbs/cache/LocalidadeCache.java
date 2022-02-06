/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.Localidade;
import ejbs.facades.LocalidadeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author benvxavier
 */
@Named(value = "localidadeCache")
@ApplicationScoped
public class LocalidadeCache implements Serializable
{

    @EJB
    private LocalidadeFacade localidadeFacade;
    private List<Localidade> paisLista, localidadeLista;
    private HashMap<Integer, Localidade> localidadeMap;

    /**
     * Creates a new instance of LocalidadeBean
     */
    public LocalidadeCache ()
    {
    }

    @PostConstruct
    public void init ()
    {
        this.localidadeLista = this.localidadeFacade.findAll();
        initLocalidadeMap();
        initPaises();
    }

    private void initLocalidadeMap ()
    {
        this.localidadeMap = new HashMap<>();
        for (Localidade l : localidadeLista)
        {
            this.localidadeMap.put(l.getPkLocalidade(), l);
        }
    }

    public Localidade find (Integer pkLocalidade)
    {
        return this.localidadeMap.get(pkLocalidade);
    }

    public String toString (List<Localidade> list)
    {
        String msg = "";
        msg += "{ ";
        boolean first = true;
        for (Localidade t : list)
        {
            if (!first)
            {
                msg += ", ";
            }
            else
            {
                first = false;
            }
            msg += localidadeFacade.toString(t);
        }
        return msg + " }";
    }

    public List<Localidade> findAllOrderedByNome (Integer pkPai)
    {
        List<Localidade> localidadeFilhos = new ArrayList<>();
        Localidade pai;
        for (Localidade l : this.localidadeLista)
        {
            pai = l.getFkLocalidade();
            if (pai == null)
            {
                continue;
            }
            if (Objects.equals(pai.getPkLocalidade(), pkPai))
            {
                localidadeFilhos.add(l);
            }
        }

        if (localidadeFilhos.size() > 1)
        {
            Collections.sort(localidadeFilhos, Comparator.comparing(Localidade::getDesignacao));
        }

        return localidadeFilhos;
    }

    public Localidade findByPaisPadrao ()
    {
        return this.findByPaisNome("Angola");
    }

    public Localidade findByPaisNome (String nome)
    {
        for (Localidade l : this.localidadeLista)
        {
            if (l.getFkLocalidade() != null)
            {
                continue;
            }
            if (l.getDesignacao().equalsIgnoreCase(nome))
            {
                return l;
            }
        }

        return null;
    }

//    public List<Localidade> findAllOrderedByNome()
//    {
//       List<Localidade> list = this.localidadeFacade.findAllOrderedByNome();
//        System.err.println("0: LocalidadeCache.findAllOrderedByNome()\tlist = " + toString(list));
//       return list;
//    }
    private void initPaises ()
    {
        //77777777777777777788888888888888888888888888
        //this.paisLista = this.localidadeFacade.findAllOrderedByNome();
        this.paisLista = new ArrayList<>();
        for (Localidade l : this.localidadeLista)
        {
            if (l.getFkLocalidade() != null)
            {
                continue;
            }
            else
            {
                this.paisLista.add(l);
            }
            
            if (paisLista.size() > 1)
            {
                Collections.sort(paisLista, Comparator.comparing(Localidade::getDesignacao));
            }
        }
    }

    //getters and setters
    public List<Localidade> getPaisLista ()
    {
        return paisLista;
    }

}

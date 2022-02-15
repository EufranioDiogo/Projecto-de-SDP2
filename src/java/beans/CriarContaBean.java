/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.cache.LocalidadeCache;
import ejbs.cache.SexoCache;
import ejbs.cache.TipoContaCache;
import ejbs.entities.Conta;
import ejbs.entities.Endereco;
import ejbs.entities.Localidade;
import ejbs.entities.Pessoa;
import ejbs.entities.Sexo;
import ejbs.entities.TipoConta;
import ejbs.facades.ContaFacade;
import ejbs.facades.EnderecoFacade;
import ejbs.facades.LocalidadeFacade;
import ejbs.facades.PessoaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.Mensagem;

/**
 *
 * @author ed
 */
@Named(value = "criarContaBean")
@ViewScoped
public class CriarContaBean implements Serializable {

    /**
     * Creates a new instance of CriarContaBean
     */
    private Conta conta;
    private Endereco endereco;
    private Pessoa pessoa;
    private Localidade localidade;
    private TipoConta tipoConta;
    private Sexo sexo;

    private List<Localidade> paisesList;
    private List<Localidade> provinciasList;
    private List<Localidade> municipiosList;
    private List<Localidade> bairroList;

    private String pkPais;
    private String pkProvincia;
    private String pkMunicipio;
    private Date birthDate;

    @EJB
    private EnderecoFacade enderecoFacade;
    @EJB
    private LocalidadeFacade localidadeFacade;
    @EJB
    private PessoaFacade pessoaFacade;
    @EJB
    private ContaFacade contaFacade;

    @Inject
    private LocalidadeCache localidadeCacheBean;
    @Inject
    private SexoCache sexoCacheBean;
    @Inject
    private TipoContaCache tipoContaCacheBean;

    public CriarContaBean() {
    }

    @PostConstruct
    public void init() {
        initEntities();
        this.initLists();
    }

    public void initEntities() {
        this.conta = new Conta();
        this.endereco = new Endereco();
        this.localidade = new Localidade();
        this.tipoConta = new TipoConta();
        this.pessoa = new Pessoa();
        this.pessoa.setFkSexo(new Sexo());
        this.endereco.setFkLocalidade(new Localidade());
        this.conta.setFkTipoConta(new TipoConta());
        this.conta.setFkPessoa(this.pessoa);

    }

    public void initLists() {
        this.pkPais = this.localidadeCacheBean.findByPaisPadrao().getPkLocalidade();
        this.paisesList = this.localidadeCacheBean.getPaisLista();
        this.provinciasList = this.localidadeCacheBean.findAllOrderedByNome(this.pkPais);
        this.municipiosList = this.localidadeCacheBean.findAllOrderedByNome(this.provinciasList.get(0).getPkLocalidade());
        this.bairroList = this.localidadeCacheBean.findAllOrderedByNome(this.municipiosList.get(0).getPkLocalidade());
    }

    public void updateProvinciaNascimentoList() {
        this.paisesList = this.localidadeCacheBean.getPaisLista();
        this.provinciasList = this.localidadeCacheBean.findAllOrderedByNome(this.pkPais);
        updateMunicipioNascimentoList();
    }

    public void updateMunicipioNascimentoList() {
        this.municipiosList = this.localidadeCacheBean.findAllOrderedByNome(this.pkProvincia);
        updateBairroNascimentoList();
    }

    public void updateBairroNascimentoList() {
        this.bairroList = this.localidadeCacheBean.findAllOrderedByNome(this.pkMunicipio);
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public List<Localidade> getPaisesList() {
        return paisesList;
    }

    public void setPaisesList(List<Localidade> paisesList) {
        this.paisesList = paisesList;
    }

    public List<Localidade> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Localidade> provinciasList) {
        this.provinciasList = provinciasList;
    }

    public List<Localidade> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(List<Localidade> municipiosList) {
        this.municipiosList = municipiosList;
    }

    public List<Localidade> getBairroList() {
        return bairroList;
    }

    public void setBairroList(List<Localidade> bairroList) {
        this.bairroList = bairroList;
    }

    public String getPkPais() {
        return pkPais;
    }

    public void setPkPais(String pkPais) {
        this.pkPais = pkPais;
    }

    public String getPkProvincia() {
        return pkProvincia;
    }

    public void setPkProvincia(String pkProvincia) {
        this.pkProvincia = pkProvincia;
    }

    public String getPkMunicipio() {
        return pkMunicipio;
    }

    public void setPkMunicipio(String pkMunicipio) {
        this.pkMunicipio = pkMunicipio;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    
    
    

    public void salvar() {
        try {
            pessoaFacade.create(pessoa);
            pessoa = this.pessoaFacade.findLastPessoa().get(0);
            
            this.conta.setFkPessoa(pessoa);
            this.contaFacade.create(conta);
            
            this.endereco.setFkPessoa(pessoa);
            this.endereco.setFkLocalidade(new Localidade(this.endereco.getFkLocalidade().getPkLocalidade()));
            this.enderecoFacade.create(endereco);
        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
            Mensagem.erroMsg(e.toString());
        }
    }

    public void limpar() {

    }

}

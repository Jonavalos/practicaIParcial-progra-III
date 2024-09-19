package pos.logic;

import pos.data.Data;
import pos.data.XmlPersister;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service() {
        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
    }

    public void stop() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void create(Activo e) throws Exception {
        Activo result = data.getActivos().stream().filter(i -> i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result == null) {
            //create(e.getCategoria());
            data.getActivos().add(e);
        }
        else throw new Exception("Error create service :(");
    }

    public Activo read(Activo e) throws Exception{
        Activo result = data.getActivos().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) {
            result.setCategoria(read(result.getCategoria()));
            return result;
        }
        else throw new Exception("Error read service :(");
    }

    public void update(Activo e) throws Exception{
        Activo result;
        try{
            result = this.read(e);
            data.getActivos().remove(result);
            data.getActivos().add(e);
        }catch (Exception ex) {
            throw new Exception("Error update service :(");
        }
    }

    public void delete(Activo e) throws Exception{
        data.getActivos().remove(e);
    }

    public List<Activo> search(Activo e){
        return  data.getActivos().stream()
                .filter(i -> i.getCodigo().contains(e.getCodigo()) && i.getCodigo().contains(e.getCodigo()))
                .sorted(Comparator.comparing(Activo::getCodigo).thenComparing(Activo::getCodigo))
                .collect(Collectors.toList());
    }
// ----------------------Categorias-------------------------

    public void create(Categoria e) throws Exception{
        Categoria result = data.getCategorias().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result == null) data.getCategorias().add(e);
    }

    public List<Categoria> search(Categoria e){
        return data.getCategorias();
    }

    public Categoria read(Categoria e){
        return data.getCategorias().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
    }



}
package co.edu.uniquindio.android.electiva.giuq.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Adaptador del contenedor de fragmentos
 * @author Francisco Alejandro Hoyos Rojas
 * @version 1.0
 */

public class AdapterPagerFragment extends FragmentPagerAdapter {
    private Context context;
    /**
     * Atributo que representa la lista de titulos del tabMenuResearchGroup
     */
    private ArrayList<String> tabTitle;
    /**
     * Atributo que representa la lista de fragmentos
     */
    private ArrayList<Fragment>fragments;

    /**
     * Atributo que representa si se van a mostrar iconos en el tab
     * true si se van a mostrar iconos, de lo contrario false
     */
    private boolean onlyIcons;


    /**
     * Fragment manager que permite realizar las transacciones
     * @param fm
     */
    public AdapterPagerFragment(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        tabTitle = new ArrayList<>();
        onlyIcons=false;

    }
    /**
     * Método que define que fragmento mostrar según la posición agregada como parámetro
     * @param position posición del fragmento en el adapter
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);

    }

    /**
     * Método que define el número de elementos a mostrar en el adapter
     * @return número de elementos
     */
    @Override
    public int getCount() {
        return tabTitle.size();
    }

    /**
     *  Método que agrega un titulo a cada tap
     * @param position posición seleccionada
     * @return título que se desea mostrar
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if(onlyIcons==false) {
            return tabTitle.get(position);
        }else return null;
    }

    /**
     * Método utilizado para agregar fragmentos al adaptador
     * @param fragment fragmento a ser agregado
     * @param title título del tab
     */
    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        tabTitle.add(title);
    }


    /**
     * Método que permite asignar un valor al atributo onlyIcons
     *
     * @param onlyIcons Valor a ser asignado al atributo onlyIcos
     */
    public void setOnlyIcons(boolean onlyIcons) {
        this.onlyIcons = onlyIcons;
    }
}

package com.proyecto.view;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase que contiene los iconos que vamos a utilizar dentro de la aplicación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Resources {

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 30;
	private static Integer width = 30;

	/**
	 * El icono para agregar un nuevo elemento.
	 */
	public static final ImageIcon ADD_ELEMENT_ICON = Resources.createImage("com/proyecto/image/add_32.png", Resources.width, Resources.height);
	/**
	 * El icono para modificar un elemento.
	 */
	public static final ImageIcon MODIFY_ELEMENT_ICON = Resources.createImage("com/proyecto/image/modify_32.png", Resources.width, Resources.height);
	/**
	 * El icono para eliminar un elemento.
	 */
	public static final ImageIcon DELETE_ELEMENT_ICON = Resources.createImage("com/proyecto/image/delete_32.png", Resources.width, Resources.height);

	/**
	 * El icono para refrescar los elementos.
	 */
	public static final ImageIcon REFRESH_ELEMENT_ICON = Resources.createImage("com/proyecto/image/refresh_32.png", Resources.width, Resources.height);
	/**
	 * El icono para seleccionar los elementos.
	 */
	public static final ImageIcon SELECT_ELEMENT_ICON = Resources.createImage("com/proyecto/image/select_32.png", Resources.width, Resources.height);
	
	/**
	 * El icono para retornar.
	 */
	public static final ImageIcon CLOSE_ICON = Resources.createImage("com/proyecto/image/close_32.png", Resources.width, Resources.height);

	/**
	 * Los archivos de los gif de progresos.
	 */
	public static final ImageIcon PROGRESS_LIST_ICON = Resources.createImage("com/proyecto/image/progress_list.gif", Resources.width, Resources.height);
	public static final ImageIcon PROGRESS_SAVE_ICON = Resources.createImage("com/proyecto/image/progress_save.gif", Resources.width, Resources.height);
	public static final ImageIcon PROGRESS_BAR_ICON = Resources.createImage("com/proyecto/image/progress_bar.gif", Resources.width, Resources.height);

	/**
	 * La función encargada de crear los iconos de acuerdo al path recibidos y al tamaño requerido de redimensión.
	 * 
	 * @param path
	 *            El path donde se encuentra la imagen que vamos a utilizar para crear el icono.
	 * @param width
	 *            El ancho con el que vamos a redimensionar el icono a crear.
	 * @param height
	 *            El alto con el que vamos a redimensionar el icono a crear.
	 * @return El icono que creamos de acuerdo a los parametros recibidos.
	 */
	public static ImageIcon createImage(String path, Integer width, Integer height) {
		return new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource(path)).getImage().getScaledInstance(width, height,
				Image.SCALE_SMOOTH));
	}
}
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
	 * El path de las imágenes.
	 */
	private static final String IMAGE_PATH = "com/proyecto/image/";

	/**
	 * Los anchos y alto de las imágenes.
	 */
	private static Integer height = 20;
	private static Integer width = 20;

	/**
	 * El icono para agregar un nuevo elemento.
	 */
	public static final ImageIcon ADD_ELEMENT_ICON = Resources.createStaticImage("add_32.png", Resources.width, Resources.height);
	/**
	 * El icono para modificar un elemento.
	 */
	public static final ImageIcon MODIFY_ELEMENT_ICON = Resources.createStaticImage("modify_32.png", Resources.width, Resources.height);
	/**
	 * El icono para eliminar un elemento.
	 */
	public static final ImageIcon DELETE_ELEMENT_ICON = Resources.createStaticImage("delete_32.png", Resources.width, Resources.height);

	/**
	 * El icono para refrescar los elementos.
	 */
	public static final ImageIcon REFRESH_ELEMENT_ICON = Resources.createStaticImage("refresh_32.png", Resources.width, Resources.height);
	/**
	 * El icono para seleccionar los elementos.
	 */
	public static final ImageIcon SELECT_ELEMENT_ICON = Resources.createStaticImage("select_32.png", Resources.width, Resources.height);

	/**
	 * El icono para confirmar una ventana.
	 */
	public static final ImageIcon COMMIT_ICON = Resources.createStaticImage("commit_32.png", Resources.width, Resources.height);
	/**
	 * El icono para cerrar una ventana.
	 */
	public static final ImageIcon CLOSE_ICON = Resources.createStaticImage("close_32.png", Resources.width, Resources.height);

	/**
	 * El icono para agregar una regla a un conjunto.
	 */
	public static final ImageIcon ADD_RULE_ICON = Resources.createStaticImage("add_rule_32.png", Resources.width, Resources.height);
	/**
	 * El icono para quitar una regla a un conjunto.
	 */
	public static final ImageIcon REMOVE_RULE_ICON = Resources.createStaticImage("remove_rule_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar el nombre de una clase a una regla.
	 */
	public static final ImageIcon CLASS_TO_RULE_ICON = Resources.createStaticImage("class_name_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar el nombre de una propiedad a una regla.
	 */
	public static final ImageIcon PROPERTY_TO_RULE_ICON = Resources.createStaticImage("property_name_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar un par de paréntesis a una regla.
	 */
	public static final ImageIcon PARENTESIS_TO_RULE_ICON = Resources.createStaticImage("parentesis_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar un par de corchetes a una regla.
	 */
	public static final ImageIcon CORCHETES_TO_RULE_ICON = Resources.createStaticImage("corchetes_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar el componentes que permite definir un error a una regla.
	 */
	public static final ImageIcon ERROR_TO_RULE_ICON = Resources.createStaticImage("error_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar el símbolo para la regla de encadenamiento hacia adelante.
	 */
	public static final ImageIcon FORWARD_RULE_ICON = Resources.createStaticImage("forward_rule_32.png", Resources.width, Resources.height);
	/**
	 * El icono de agregar el símbolo para la regla de encadenamiento hacia atrás.
	 */
	public static final ImageIcon BACKWARD_RULE_ICON = Resources.createStaticImage("backward_rule_32.png", Resources.width, Resources.height);

	/**
	 * El icono para una pantalla de CRUD.
	 */
	public static final ImageIcon CRUD_ICON = Resources.createStaticImage("crud_32.png", Resources.width, Resources.height);

	/**
	 * El icono para imprimir un archivo PDF.
	 */
	public static final ImageIcon REPORT_PDF_ICON = Resources.createStaticImage("file_pdf_32.png", Resources.width, Resources.height);

	/**
	 * El icono para procesado.
	 */
	public static final ImageIcon PROCCESS_ICON = Resources.createStaticImage("proccess_32.png", Resources.width, Resources.height);

	/**
	 * El icono de borrado de un resultado.
	 */
	public static final ImageIcon ERASE_ICON = Resources.createStaticImage("eraser_32.png", Resources.width, Resources.height);

	/**
	 * Los archivos de los GIF de progresos.
	 */
	public static final ImageIcon PROGRESS_LIST_ICON = Resources.createGifImage("progress_list.gif");

	/**
	 * La función encargada de crear los iconos de acuerdo al path recibidos y al tamaño requerido de redimensión.
	 * 
	 * @param path
	 *            El path donde se encuentra la imagen que vamos a utilizar para crear el icono.
	 * @param width
	 *            El ancho con el que vamos a redimensionar el icono a crear.
	 * @param height
	 *            El alto con el que vamos a redimensionar el icono a crear.
	 * @return El icono que creamos de acuerdo a los parámetros recibidos.
	 */
	public static ImageIcon createStaticImage(String path, Integer width, Integer height) {
		return new ImageIcon(new ImageIcon(Resources.class.getClassLoader().getResource(Resources.IMAGE_PATH + path)).getImage().getScaledInstance(
				width, height, Image.SCALE_SMOOTH));
	}

	/**
	 * La función encargada de crear los iconos animado de acuerdo al path recibidos.
	 * 
	 * @param path
	 *            El path donde se encuentra la imagen en formato GIF que vamos a utilizar para crear el icono.
	 * @return El icono animado que creamos de acuerdo a los parámetros recibidos.
	 */
	public static ImageIcon createGifImage(String path) {
		return new ImageIcon(Resources.class.getClassLoader().getResource(Resources.IMAGE_PATH + path));
	}
}
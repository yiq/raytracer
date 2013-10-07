package teamk.hw4.model.material;

/**
 * A list of supported material types to hint the renderer how to treat the color value
 * 
 * @author Yi Qiao
 *
 */
public enum TKMaterialTypes {
	SIMPLE,						// Simply returns the intrinsic color
	MIRROR,						// A mirror that reflects other objects
	UNDEFINED					// No material is defined
}

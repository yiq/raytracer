package teamk.hw4.model.material;

/**
 * Returns MIRROR as the material type at any point in uv system
 * 
 * @author Yi Qiao
 *
 */
public class TKSimpleMirrorMaterial extends TKAbstractMaterial {

	@Override
	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double u, double v) {
		return TKMaterialTypes.MIRROR;
	}

}

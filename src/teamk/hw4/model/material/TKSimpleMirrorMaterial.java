package teamk.hw4.model.material;

public class TKSimpleMirrorMaterial extends TKAbstractMaterial {

	@Override
	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double ux, double uy) {
		return TKMaterialTypes.MIRROR;
	}

}

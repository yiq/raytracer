package teamk.hw4.model;

public class TKMirrorMaterial extends TKAbstractMaterial {

	@Override
	public TKMaterialTypes getMaterialTypeAtUVCoordinate(double ux, double uy) {
		return TKMaterialTypes.MIRROR;
	}

}

package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class FBXExporter {

	public static void exportWorld() {
		JFileChooser saveFile = new JFileChooser();
		int returnVal = saveFile.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				String path = saveFile.getSelectedFile().getAbsolutePath();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
				writeWorld(writer);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeWorld(BufferedWriter writer) throws IOException {
		writer.write("; FBX planet file\n" +
				             "; ----------------------------------------------------\n" +
				             "\n" +
				             "FBXHeaderExtension:  {\n" +
				             "\tFBXHeaderVersion: 1003\n" +
				             "\tFBXVersion: 6100\n" +
				             "\tCreationTimeStamp:  {\n" +
				             "\t\tVersion: 1000\n" +
				             "\t\tYear: " + Calendar.getInstance().get(Calendar.YEAR) + "\n" +
				             "\t\tMonth: " + Calendar.getInstance().get(Calendar.MONTH) + "\n" +
				             "\t\tDay: " + Calendar.getInstance().get(Calendar.DATE) + "\n" +
				             "\t\tHour: " + Calendar.getInstance().get(Calendar.HOUR) + "\n" +
				             "\t\tMinute: " + Calendar.getInstance().get(Calendar.MINUTE) + "\n" +
				             "\t\tSecond: " + Calendar.getInstance().get(Calendar.SECOND) + "\n" +
				             "\t\tMillisecond: " + Calendar.getInstance().get(Calendar.MILLISECOND) + "\n" +
				             "\t}\n" +
				             "\tCreator: \"Custom FBX Exporter written for PlanetBuilder\"\n" +
				             "\tOtherFlags:  {\n" +
				             "\t\tFlagPLE: 0\n" +
				             "\t}\n" +
				             "}\n" +
				             "CreationTime: " +
				             Calendar.getInstance().get(Calendar.YEAR) + "-" +
				             Calendar.getInstance().get(Calendar.MONTH) + "-" +
				             Calendar.getInstance().get(Calendar.DATE) + "  " +
				             Calendar.getInstance().get(Calendar.HOUR) + ":" +
				             Calendar.getInstance().get(Calendar.MINUTE) + ":" +
				             Calendar.getInstance().get(Calendar.SECOND) + ":" +
				             Calendar.getInstance().get(Calendar.MILLISECOND) + "\"\n" +
				             "Creator: \"PlanetBuilder\"");

		writer.write("; Object definitions\n" +
				             ";------------------------------------------------------------------\n" +
				             "\n" +
				             "Definitions:  {\n" +
				             "\tVersion: 100\n" +
				             "\tCount: 3\n" +
				             "\tObjectType: \"Model\" {\n" +
				             "\t\tCount: 1\n" +
				             "\t}\n" +
				             "\tObjectType: \"Geometry\" {\n" +
				             "\t\tCount: 1\n" +
				             "\t}\n" +
				             "\tObjectType: \"GlobalSettings\" {\n" +
				             "\t\tCount: 1\n" +
				             "\t}\n" +
				             "}");

		writer.write("; Object properties\n" +
				             ";------------------------------------------------------------------\n" +
				             "\n" +
				             "Objects:  {\n" +
				             "\tModel: \"Model::Planet\", \"Mesh\" {\n" +
				             "\t\tVersion: 232\n" +
				             "\t\tProperties60:  {\n" +
				             "\t\t\tProperty: \"QuaternionInterpolate\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"Visibility\", \"Visibility\", \"A+\",1\n" +
				             "\t\t\tProperty: \"Lcl Translation\", \"Lcl Translation\", \"A+\",0.000000000000000,0.000000000000000,0.000000000000000\n" +
				             "\t\t\tProperty: \"Lcl Rotation\", \"Lcl Rotation\", \"A+\",0.000000000000000,0.000000000000000,0.000000000000000\n" +
				             "\t\t\tProperty: \"Lcl Scaling\", \"Lcl Scaling\", \"A+\",1.000000000000000,1.000000000000000,1.000000000000000\n" +
				             "\t\t\tProperty: \"RotationOffset\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"RotationPivot\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"ScalingOffset\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"ScalingPivot\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"TranslationActive\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"TranslationMin\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"TranslationMax\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"TranslationMinX\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"TranslationMinY\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"TranslationMinZ\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"TranslationMaxX\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"TranslationMaxY\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"TranslationMaxZ\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationOrder\", \"enum\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationSpaceForLimitOnly\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"AxisLen\", \"double\", \"\",10\n" +
				             "\t\t\tProperty: \"PreRotation\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"PostRotation\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"RotationActive\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationMin\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"RotationMax\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"RotationMinX\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationMinY\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationMinZ\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationMaxX\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationMaxY\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationMaxZ\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationStiffnessX\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationStiffnessY\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"RotationStiffnessZ\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MinDampRangeX\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MinDampRangeY\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MinDampRangeZ\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MaxDampRangeX\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MaxDampRangeY\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MaxDampRangeZ\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MinDampStrengthX\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MinDampStrengthY\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MinDampStrengthZ\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MaxDampStrengthX\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MaxDampStrengthY\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"MaxDampStrengthZ\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"PreferedAngleX\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"PreferedAngleY\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"PreferedAngleZ\", \"double\", \"\",0\n" +
				             "\t\t\tProperty: \"InheritType\", \"enum\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingActive\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingMin\", \"Vector3D\", \"\",1,1,1\n" +
				             "\t\t\tProperty: \"ScalingMax\", \"Vector3D\", \"\",1,1,1\n" +
				             "\t\t\tProperty: \"ScalingMinX\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingMinY\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingMinZ\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingMaxX\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingMaxY\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"ScalingMaxZ\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"GeometricTranslation\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"GeometricRotation\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"GeometricScaling\", \"Vector3D\", \"\",1,1,1\n" +
				             "\t\t\tProperty: \"LookAtProperty\", \"object\", \"\"\n" +
				             "\t\t\tProperty: \"UpVectorProperty\", \"object\", \"\"\n" +
				             "\t\t\tProperty: \"Show\", \"bool\", \"\",1\n" +
				             "\t\t\tProperty: \"NegativePercentShapeSupport\", \"bool\", \"\",1\n" +
				             "\t\t\tProperty: \"DefaultAttributeIndex\", \"int\", \"\",0\n" +
				             "\t\t\tProperty: \"Color\", \"Color\", \"A\",0.8,0.8,0.8\n" +
				             "\t\t\tProperty: \"Size\", \"double\", \"\",100\n" +
				             "\t\t\tProperty: \"Look\", \"enum\", \"\",1\n" +
				             "\t\t}\n" +
				             "\t\tMultiLayer: 0\n" +
				             "\t\tMultiTake: 1\n" +
				             "\t\tShading: Y\n" +
				             "\t\tCulling: \"CullingOff\"\n" +
				             "\t\tVertices: "
		);

		float[] vertices = World.getInstance().getSurfaceVertexArray();
		for(int vertex = 0; vertex < vertices.length - 1; vertex++) {
			writer.write(String.valueOf(vertices[vertex]) + ",");
		}

		writer.write(String.valueOf(vertices[vertices.length - 1]) + "\n");
		writer.write("\t\tPolygonVertexIndex: ");

		for(int vertex = 0; vertex < vertices.length - 1; vertex++) {
			if(vertex % 3 != 0) {
				writer.write(String.valueOf(vertex) + ",");
			} else {
				writer.write(String.valueOf((vertex * -1) - 1) + ",");
			}
		}

		writer.write(String.valueOf(((vertices.length - 1) * -1) - 1) + "\n");

		writer.write("\t\tGeometryVersion: 124\n" +
				             "\t\tLayerElementNormal: 0 {\n" +
				             "\t\t\tVersion: 101\n" +
				             "\t\t\tName: \"\"\n" +
				             "\t\t\tMappingInformationType: \"ByPolygonVertex\"\n" +
				             "\t\t\tReferenceInformationType: \"Direct\"\n" +
				             "\t\t\tNormals: 1.000000,0.000000,0.000000,1.000000,0.000000,0.000000,1.000000,0.000000,0.000000,1.000000,0.000000,0.000000\n" +
				             "\t\t}\n" +
				             "\t\tLayerElementSmoothing: 0 {\n" +
				             "\t\t\tVersion: 102\n" +
				             "\t\t\tName: \"\"\n" +
				             "\t\t\tMappingInformationType: \"ByPolygon\"\n" +
				             "\t\t\tReferenceInformationType: \"Direct\"\n" +
				             "\t\t\tSmoothing: 1\n" +
				             "\t\t}\n" +
				             "\t\tLayerElementUV: 0 {\n" +
				             "\t\t\tVersion: 101\n" +
				             "\t\t\tName: \"UVMap\"\n" +
				             "\t\t\tMappingInformationType: \"ByPolygonVertex\"\n" +
				             "\t\t\tReferenceInformationType: \"IndexToDirect\"\n" +
				             "\t\t\tUV: 0.493900,0.746800,0.630800,0.745600,0.502100,0.684400,0.634900,0.684300\n" +
				             "\t\t\tUVIndex: 2,3,1,0\n" +
				             "\t\t}\n" +
				             "\t\tLayerElementTexture: 0 {\n" +
				             "\t\t\tVersion: 101\n" +
				             "\t\t\tName: \"\"\n" +
				             "\t\t\tMappingInformationType: \"NoMappingInformation\"\n" +
				             "\t\t\tReferenceInformationType: \"IndexToDirect\"\n" +
				             "\t\t\tBlendMode: \"Translucent\"\n" +
				             "\t\t\tTextureAlpha: 1\n" +
				             "\t\t\tTextureId: \n" +
				             "\t\t}\n" +
				             "\t\tLayerElementMaterial: 0 {\n" +
				             "\t\t\tVersion: 101\n" +
				             "\t\t\tName: \"\"\n" +
				             "\t\t\tMappingInformationType: \"AllSame\"\n" +
				             "\t\t\tReferenceInformationType: \"IndexToDirect\"\n" +
				             "\t\t\tMaterials: 0\n" +
				             "\t\t}\n" +
				             "\t\tLayer: 0 {\n" +
				             "\t\t\tVersion: 100\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementNormal\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementSmoothing\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementUV\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementTexture\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementMaterial\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t}\n" +
				             "\t}\n" +
				             "\tGlobalSettings:  {\n" +
				             "\t\tVersion: 1000\n" +
				             "\t\tProperties60:  {\n" +
				             "\t\t\tProperty: \"UpAxis\", \"int\", \"\",1\n" +
				             "\t\t\tProperty: \"UpAxisSign\", \"int\", \"\",1\n" +
				             "\t\t\tProperty: \"FrontAxis\", \"int\", \"\",2\n" +
				             "\t\t\tProperty: \"FrontAxisSign\", \"int\", \"\",1\n" +
				             "\t\t\tProperty: \"CoordAxis\", \"int\", \"\",0\n" +
				             "\t\t\tProperty: \"CoordAxisSign\", \"int\", \"\",1\n" +
				             "\t\t\tProperty: \"UnitScaleFactor\", \"double\", \"\",1\n" +
				             "\t\t}\n" +
				             "\t}\n" +
				             "}");





	}
}

package uk.ac.cam.cl.bravo.PlanetBuilder;

import com.hackoeur.jglm.Vec3;

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
		writer.write("; FBX 6.1.0 project file\n" +
				             "; FBX planet file\n" +
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
				             "CreationTime: \"" +
				             Calendar.getInstance().get(Calendar.YEAR) + "-" +
				             Calendar.getInstance().get(Calendar.MONTH) + "-" +
				             Calendar.getInstance().get(Calendar.DATE) + " " +
				             Calendar.getInstance().get(Calendar.HOUR) + ":" +
				             Calendar.getInstance().get(Calendar.MINUTE) + ":" +
				             Calendar.getInstance().get(Calendar.SECOND) + ":" +
				             Calendar.getInstance().get(Calendar.MILLISECOND) + "\"\n" +
				             "Creator: \"PlanetBuilder\"\n\n");

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
				             "\tObjectType: \"Material\" {\n" +
				             "\t\tCount: 1\n" +
				             "\t}\n" +
				             "\tObjectType: \"Pose\" {\n" +
				             "\t\tCount: 1\n" +
				             "\t}\n" +
				             "\tObjectType: \"GlobalSettings\" {\n" +
				             "\t\tCount: 1\n" +
				             "\t}\n" +
				             "}\n\n");

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
				             "\t\t\tProperty: \"Lcl Rotation\", \"Lcl Rotation\", \"A+\",0.000000000000000,-0.000000000000000,0.000000000000000\n" +
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

		/*writer.write("1.000000,1.000000,-1.000000,1.000000,-1.000000,-1.000000,-1.000000,-1.000000,-1.000000,-1.000000,1.000000,-1.000000,1.000000,0.999999,1.000000,0.999999,-1.000001,1.000000,-1.000000,-1.000000,1.000000" +
				             ",-1.000000,1.000000,1.000000\n");
		writer.write("\t\tPolygonVertexIndex: ");
		writer.write("0,1,2,-4,4,7,6,-6,0,4,5,-2,1,5,6,-3,2,6,7,-4,4,0,3,-8");*/

		float[] vertices = World.getInstance().getSurfaceVertexArray();
		for(int vertex = 0; vertex < vertices.length - 1; vertex++) {
			writer.write(String.valueOf(vertices[vertex]) + ",");
		}

		writer.write(String.valueOf(vertices[vertices.length - 1]) + "\n");
		writer.write("\t\tPolygonVertexIndex: ");

		for(int vertex = 0; vertex < vertices.length / 3 - 1; vertex++) {
			if(vertex % 3 != 2) {
				writer.write(String.valueOf(vertex) + ",");
			} else {
				writer.write(String.valueOf((vertex * -1) - 1) + ",");
			}
		}

		writer.write(String.valueOf(((vertices.length / 3 - 1) * -1) - 1) + "\n");

		writer.write("\t\tEdges: \n" +
				             "\t\tGeometryVersion: 124\n" +
				             "\t\tLayerElementNormal: 0 {\n" +
				             "\t\t\tVersion: 101\n" +
				             "\t\t\tName: \"\"\n" +
				             "\t\t\tMappingInformationType: \"ByVertice\"\n" +
				             "\t\t\tReferenceInformationType: \"Direct\"\n" +
				             "\t\t\tNormals: ");

		/*writer.write("0.577349185943604,0.577349185943604,-0.577349185943604,0.577349185943604,-0.577349185943604,-0.577349185943604" +
				             ",-0.577349185943604,-0.577349185943604,-0.577349185943604,-0.577349185943604,0.577349185943604,-0.577349185943604" +
				             ",0.577349185943604,0.577349185943604,0.577349185943604,0.577349185943604,-0.577349185943604,0.577349185943604" +
				             ",-0.577349185943604,-0.577349185943604,0.577349185943604,-0.577349185943604,0.577349185943604,0.577349185943604\n");
*/
		for(int vertex = 0; vertex < vertices.length - 3; vertex += 3) {
			float vertexX = vertices[vertex];
			float vertexY = vertices[vertex + 1];
			float vertexZ = vertices[vertex + 2];
			Vec3 vec = new Vec3(vertexX, vertexY, vertexZ);
			vec = vec.getUnitVector();
			//String vertexXFormatted = String.format("%-17s", String.valueOf(vertices)).replace(' ', '0');
			writer.write(String.valueOf(vec.getX()) + "," + vec.getY() + "," + vec.getZ() + ",");
		}

		float vertexX = vertices[vertices.length - 3];
		float vertexY = vertices[vertices.length - 2];
		float vertexZ = vertices[vertices.length - 1];
		Vec3 vec = new Vec3(vertexX, vertexY, vertexZ);
		vec = vec.getUnitVector();
		writer.write(String.valueOf(vec.getX()) + "," + vec.getY() + "," + vec.getZ() + "\n");

		writer.write("\t\t}\n" +
				             "\t\tLayerElementColor: 0 {\n" +
				             "\t\t\tVersion: 101\n" +
				             "\t\t\tName: \"Col\"\n" +
				             "\t\t\tMappingInformationType: \"ByPolygonVertex\"\n" +
				             "\t\t\tReferenceInformationType: \"Direct\"\n" +
				             "\t\t\tColors: ");

		/*writer.write("1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1" +
				             ",1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1" +
				             ",1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1" +
				             ",1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1,1.0000,1.0000,1.0000,1\n");
		writer.write("ColorIndex: 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23\n");*/

		float[] colors = World.getInstance().getSurfaceColorArray();
		for(int color = 0; color < colors.length - 1; color++) {
			writer.write(String.valueOf(colors[color]) + ",");
		}

		writer.write(String.valueOf(vertices[vertices.length - 1]) + "\n");

		writer.write("\t\t}\n" +
				             "\t\tLayer: 0 {\n" +
				             "\t\t\tVersion: 100\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementNormal\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t\tLayerElement:  {\n" +
				             "\t\t\t\tType: \"LayerElementColor\"\n" +
				             "\t\t\t\tTypedIndex: 0\n" +
				             "\t\t\t}\n" +
				             "\t\t}\n" +
				             "\t}\n" +
							 "Material: \"Material::unnamed\", \"\" {\n" +
				             "\t\tVersion: 102\n" +
				             "\t\tShadingModel: \"phong\"\n" +
				             "\t\tMultiLayer: 0\n" +
				             "\t\tProperties60:  {\n" +
				             "\t\t\tProperty: \"ShadingModel\", \"KString\", \"\", \"Phong\"\n" +
				             "\t\t\tProperty: \"MultiLayer\", \"bool\", \"\",0\n" +
				             "\t\t\tProperty: \"EmissiveColor\", \"ColorRGB\", \"\",0.8000,0.8000,0.8000\n" +
				             "\t\t\tProperty: \"EmissiveFactor\", \"double\", \"\",0.0000\n" +
				             "\t\t\tProperty: \"AmbientColor\", \"ColorRGB\", \"\",0.0000,0.0000,0.0000\n" +
				             "\t\t\tProperty: \"AmbientFactor\", \"double\", \"\",0.5000\n" +
				             "\t\t\tProperty: \"DiffuseColor\", \"ColorRGB\", \"\",0.8000,0.8000,0.8000\n" +
				             "\t\t\tProperty: \"DiffuseFactor\", \"double\", \"\",1.0000\n" +
				             "\t\t\tProperty: \"Bump\", \"Vector3D\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"TransparentColor\", \"ColorRGB\", \"\",1,1,1\n" +
				             "\t\t\tProperty: \"TransparencyFactor\", \"double\", \"\",0.0000\n" +
				             "\t\t\tProperty: \"SpecularColor\", \"ColorRGB\", \"\",0.8000,0.8000,0.8000\n" +
				             "\t\t\tProperty: \"SpecularFactor\", \"double\", \"\",0.2000\n" +
				             "\t\t\tProperty: \"ShininessExponent\", \"double\", \"\",80.0\n" +
				             "\t\t\tProperty: \"ReflectionColor\", \"ColorRGB\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"ReflectionFactor\", \"double\", \"\",1\n" +
				             "\t\t\tProperty: \"Emissive\", \"ColorRGB\", \"\",0,0,0\n" +
				             "\t\t\tProperty: \"Ambient\", \"ColorRGB\", \"\",0.0,0.0,0.0\n" +
				             "\t\t\tProperty: \"Diffuse\", \"ColorRGB\", \"\",0.8,0.8,0.8\n" +
				             "\t\t\tProperty: \"Specular\", \"ColorRGB\", \"\",0.8,0.8,0.8\n" +
				             "\t\t\tProperty: \"Shininess\", \"double\", \"\",20.0\n" +
				             "\t\t\tProperty: \"Opacity\", \"double\", \"\",1.0\n" +
				             "\t\t\tProperty: \"Reflectivity\", \"double\", \"\",0\n" +
				             "\t\t}\n" +
				             "\t}\n" +
				             "\tPose: \"Pose::BIND_POSES\", \"BindPose\" {\n" +
				             "\t\tType: \"BindPose\"\n" +
				             "\t\tVersion: 100\n" +
				             "\t\tProperties60:  {\n" +
				             "\t\t}\n" +
				             "\t\tNbPoseNodes: 1\n" +
				             "\t\tPoseNode:  {\n" +
				             "\t\t\tNode: \"Model::Cube\"\n" +
				             "\t\t\tMatrix: 1.000000000000000,0.000000000000000,0.000000000000000,0.000000000000000,0.000000000000000,1.000000000000000,0.000000000000000,0.000000000000000,0.000000000000000,0.000000000000000,1.000000000000000,0.000000000000000,0.000000000000000,0.000000000000000,0.000000000000000,1.000000000000000\n" +
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
				             "}\n\n");

		writer.write("; Object relations\n" +
				             ";------------------------------------------------------------------\n" +
				             "\n" +
				             "Relations:  {\n" +
				             "\tModel: \"Model::Cube\", \"Mesh\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Perspective\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Top\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Bottom\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Front\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Back\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Right\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Producer Left\", \"Camera\" {\n" +
				             "\t}\n" +
				             "\tModel: \"Model::Camera Switcher\", \"CameraSwitcher\" {\n" +
				             "\t}\n" +
				             "\tMaterial: \"Material::unnamed\", \"\" {\n" +
				             "\t}\n" +
				             "}\n" +
				             "\n" +
				             "; Object connections\n" +
				             ";------------------------------------------------------------------\n" +
				             "\n" +
				             "Connections:  {\n" +
				             "\tConnect: \"OO\", \"Model::Planet\", \"Model::Scene\"\n" +
				             "}\n" +
				             ";Takes and animation section\n" +
				             ";----------------------------------------------------\n" +
				             "\n" +
				             "Takes:  {\n" +
				             "\tCurrent: \"\"\n" +
				             "}\n" +
				             ";Version 5 settings\n" +
				             ";------------------------------------------------------------------\n" +
				             "\n" +
				             "Version5:  {\n" +
				             "\tAmbientRenderSettings:  {\n" +
				             "\t\tVersion: 101\n" +
				             "\t\tAmbientLightColor: 0.0,0.0,0.0,0\n" +
				             "\t}\n" +
				             "\tFogOptions:  {\n" +
				             "\t\tFlogEnable: 0\n" +
				             "\t\tFogMode: 0\n" +
				             "\t\tFogDensity: 0.000\n" +
				             "\t\tFogStart: 5.000\n" +
				             "\t\tFogEnd: 25.000\n" +
				             "\t\tFogColor: 0.1,0.1,0.1,1\n" +
				             "\t}\n" +
				             "\tSettings:  {\n" +
				             "\t\tFrameRate: \"24\"\n" +
				             "\t\tTimeFormat: 1\n" +
				             "\t\tSnapOnFrames: 0\n" +
				             "\t\tReferenceTimeIndex: -1\n" +
				             "\t\tTimeLineStartTime: 0\n" +
				             "\t\tTimeLineStopTime: 479181389250\n" +
				             "\t}\n" +
				             "\tRendererSetting:  {\n" +
				             "\t\tDefaultCamera: \"Producer Perspective\"\n" +
				             "\t\tDefaultViewingMode: 0\n" +
				             "\t}\n" +
				             "}");




	}
}

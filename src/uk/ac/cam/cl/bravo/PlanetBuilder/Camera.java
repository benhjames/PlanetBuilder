package uk.ac.cam.cl.bravo.PlanetBuilder;

import com.hackoeur.jglm.*;

public class Camera {
	private Vec3 position = new Vec3(0.0f, 0.0f, 0.0f);
	private float horizontalAngle = 0.0f;
	private float verticalAngle = 0.0f;
	private float fieldOfView = 60.0f;
	private float nearPlane = 1.0f;
	private float farPlane = 500.0f;
	private float distanceFromCenter = 3.0f;
	private float viewportAspectRatio = (float)MainClass.canvasWidth/(float)MainClass.canvasHeight;

	public Camera() {}

	public Vec3 getPosition() { return position; }
	public void setPosition(Vec3 pos) { position = pos; }
	public void updatePosition() { circle(0,0); }

	public void offsetPosition(Vec3 offset) { position = position.add(offset); }

	public float getFOV() { return fieldOfView; }
	public void setFOV(float fov) { fieldOfView = fov; }

	public float getNearPlane() { return nearPlane; }
	public void setNearPlane(float plane) {
		if(plane < farPlane && plane > 0.0f)
			nearPlane = plane;
		else
			System.err.println("Trying to set near plane further than far plane or at 0.0f!");
	}

	public float getFarPlane() { return farPlane; }
	public void setFarPlane(float plane) {
		if(plane > nearPlane)
			farPlane = plane;
		else
			System.err.println("Trying to set far plane closer than near plane!");
	}

	public float getHorizontalAngle() {
		return -horizontalAngle;
	}
	public float getVerticalAngle() {
		return verticalAngle;
	}

	public Mat4 orientation() {
		Mat4 orientation = Mat4.MAT4_IDENTITY;
		orientation = orientation.multiply(Matrices.rotate((float)Math.toRadians(verticalAngle), new Vec3(1.0f, 0.0f, 0.0f)));
		orientation = orientation.multiply(Matrices.rotate((float)Math.toRadians(horizontalAngle), new Vec3(0.0f, 1.0f, 0.0f)));
		return orientation;
	}

	public void offsetOrientation(float upAngle, float rightAngle) {
		horizontalAngle += rightAngle;
		verticalAngle += upAngle;
	}

	public void lookAt(Vec3 target) {
		if(target != position) {
			Vec3 direction = (target.subtract(position)).getUnitVector().multiply(-1.0f);
			verticalAngle = (float)Math.toDegrees(Math.asin(direction.getY()));
			horizontalAngle = -(float)Math.toDegrees(Math.atan2(direction.getX(), direction.getZ()));
			normalizeAngles();
		}
	}

	//Circle in the sense that birds circle
	public void circle(double x, double y) {
		float hAngle = getHorizontalAngle() + (float)x;
		float vAngle = getVerticalAngle() + (float)y;
		if(vAngle > 89.9f)
			vAngle = 89.9f;
		else if(vAngle < -89.9f)
			vAngle = -89.9f;
		setPosition(new Vec3((float) Math.sin(Math.toRadians(hAngle)) * distanceFromCenter * (float) Math.cos(Math.toRadians(vAngle)), (float) Math.sin(Math.toRadians(vAngle)) * distanceFromCenter, (float) Math.cos(Math.toRadians(hAngle)) * distanceFromCenter * (float) Math.cos(Math.toRadians(vAngle))));
		lookAt(new Vec3(0.0f, 0.0f, 0.0f));
	}

	public void zoom(float x) {
		distanceFromCenter += x;
		if (distanceFromCenter < 2.5f) {
			distanceFromCenter = 2.5f;
		}
		else if (distanceFromCenter > 7.5f) {
			distanceFromCenter = 7.5f;
		}
		updatePosition();
	}

	public float getViewportAspectRatio() {
		return viewportAspectRatio;
	}

	public void setViewportAspectRatio(float ratio) {
		if(ratio > 0.0f)
			viewportAspectRatio = ratio;
	}

	public Mat4 matrix() {
		return projection().multiply(view());
	}

	public Mat4 projection() {
		return Matrices.perspective(fieldOfView, viewportAspectRatio, nearPlane, farPlane);
	}

	public Mat4 view() {
		return orientation().multiply(Mat4.MAT4_IDENTITY.translate(position.multiply(-1.0f)));
	}

	public void normalizeAngles() {
		horizontalAngle = horizontalAngle % 360.0f;
		if(horizontalAngle < 0.0f)
			horizontalAngle += 360.0f;
		if(verticalAngle > 89.9f)
			verticalAngle = 89.9f;
		else if(verticalAngle < -89.9f)
			verticalAngle = -89.9f;
	}

}

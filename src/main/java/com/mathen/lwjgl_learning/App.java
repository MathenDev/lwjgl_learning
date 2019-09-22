package com.mathen.lwjgl_learning;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;

public class App {

	static void processInput(long window) {
		if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
			glfwSetWindowShouldClose(window, true);
		}
	}

	public static void main(String[] args) {
		glfwInit();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(800, 600, "LearnOpenGL", NULL, NULL);
		if (window == NULL) {
			glfwTerminate();
			throw new RuntimeException("Cannot create window");
		} else {
//			Logs.globalInfo(String.format("Window created: %d%n", window));
		}
		glfwMakeContextCurrent(window);

		// When window size changed, reset viewport adapting the size
		glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {

			public void invoke(long window, int width, int height) {
				glViewport(0, 0, width, height);
			}
		});

		// very important!
		GL.createCapabilities();
		glfwShowWindow(window);
		// keep window running
		while (!glfwWindowShouldClose(window)) {
			processInput(window);
			glClearColor(0.2f, 0.3f, 0.3f, 1f);
			glClear(GL_COLOR_BUFFER_BIT);
			// system using two buffers, one for displaying, another for preparing, swapping
			// them mean show the preparing one out
			glfwSwapBuffers(window);
			// processing remaining events
			glfwPollEvents();
		}

		glfwTerminate();
	}
}
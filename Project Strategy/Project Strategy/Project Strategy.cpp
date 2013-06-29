// Project Strategy.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include "GLee.h"
#include <GLFW/glfw3.h>

using namespace std;

int main()
{
	cout << "Hello World!";

	glfwInit();

	char exitchar;
	cin.getline(&exitchar, 1);

	return 0;
}


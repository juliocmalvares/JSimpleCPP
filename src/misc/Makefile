all:
	jflex scanner.flex
	byaccj -Jclass=Parser -Jpackage=compiler -Jsemantic=Object -Jnorun -Jextends=Scanner -Jnoconstruct -Jthrows=IOException parser.y 
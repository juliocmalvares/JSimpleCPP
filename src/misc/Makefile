all:
	jflex scanner.flex
	byaccj -Jclass=Parser -Jsemantic=Object -Jnorun -Jextends=Scanner -Jnoconstruct -Jthrows=Exception parser.y 
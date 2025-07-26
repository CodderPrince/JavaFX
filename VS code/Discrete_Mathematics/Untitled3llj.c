enum Color {
  RED = 1,
  GREEN = 2,
  YELLOW = 3,
  BLUE = 4
};

int main() {
  enum Color lightColor = RED;

  if (lightColor == RED) {
    printf("The light is red.\n");
  } else if (lightColor == GREEN) {
    printf("The light is green.\n");
  } else if (lightColor == YELLOW) {
    printf("The light is yellow.\n");
  } else {
    printf("The light is blue.\n");
  }

  return 0;
}

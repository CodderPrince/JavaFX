#include <stdio.h>
#include <string.h>

int main() {
  int t;
  scanf("%d", &t);
  while (t--) {
    char s[100];
    scanf("%s", s);
    int color_count[15] = {0};
    for (int i = 0; i < strlen(s); i++) {
      color_count[s[i] - 'a']++;
    }
    int max_color_count = 0;
    for (int i = 0; i < 15; i++) {
      if (color_count[i] > max_color_count) {
        max_color_count = color_count[i];
      }
    }

    // Check if all the colors can be created simultaneously.
    int can_create_all_colors = 1;
    for (int i = 0; i < 15; i++) {
      if (color_count[i] > 0) {
        for (int j = i + 1; j < 15; j++) {
          if (color_count[j] > 0 && s[i] == s[j]) {
            can_create_all_colors = 0;
            break;
          }
        }
        if (!can_create_all_colors) {
          break;
        }
      }
    }

    if (can_create_all_colors) {
      printf("%d\n", max_color_count);
    } else {
      printf("0\n");
    }
  }
  return 0;
}

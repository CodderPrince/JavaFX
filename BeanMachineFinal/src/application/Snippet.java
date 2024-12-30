package application;

public class Snippet {
	private void dropBallsSequentially(Pane pane) {
	    Random random = new Random();
	
	    // Clear only balls from the pane, assuming balls have a specific ID format or type
	    pane.getChildren().removeIf(node -> node instanceof Circle && node.getId().startsWith("ball"));
	
	    // Reset the count of balls in each slot
	    Arrays.fill(slots, 0);
	
	    for (int i = 0; i < ballCount; i++) {
	        final int ballIndex = i;
	
	        PauseTransition delay = new PauseTransition(Duration.seconds(i * 1.5));
	        delay.setOnFinished(event -> {
	            // Create a ball with a random color
	            Circle ball = new Circle(450, 100, BALL_RADIUS);
	            ball.setFill(randomColor());
	            ball.setId("ball" + ballIndex); // Ensure each ball has a unique ID
	            pane.getChildren().add(ball);
	
	            // Create a path for the ball
	            Path path = new Path();
	            path.getElements().add(new MoveTo(ball.getCenterX(), ball.getCenterY()));
	
	            // Simulate the ball bouncing through the pegs
	            for (Circle peg : pegs) {
	                double bounceDirection = random.nextBoolean() ? -15 : 15;
	                path.getElements().add(new QuadCurveTo(
	                        peg.getCenterX(), peg.getCenterY(),
	                        peg.getCenterX() + bounceDirection, peg.getCenterY() + 20
	                ));
	            }
	
	            // Determine the final slot position
	            int slotsCount = SLOT_COUNT;
	            double slotWidth = SLOT_WIDTH;
	            int finalSlotIndex = random.nextInt(slotsCount);
	
	            // Calculate the final X position
	            double finalX = 410 - (slotsCount / 2) * slotWidth + finalSlotIndex * slotWidth + slotWidth / 2;
	
	            // Increment ball count in the chosen slot
	            slots[finalSlotIndex]++;
	
	            // Calculate the final Y position to avoid overlapping
	            double finalY = 780 - slots[finalSlotIndex] * (BALL_RADIUS * 2 + 10);
	
	            // Control point for a smooth curve
	            double controlY = finalY - 130;
	            path.getElements().add(new QuadCurveTo(
	                    (finalX + ball.getCenterX()) / 2, controlY,
	                    finalX, finalY
	            ));
	
	            // Animate the ball along the path
	            PathTransition pathTransition = new PathTransition(Duration.seconds(8), path, ball);
	            pathTransition.setCycleCount(1);
	            pathTransition.setOnFinished(ev -> {
	                // Update the label to reflect the new ball count in the slot
	                slotLabels[finalSlotIndex].setText("Slot " + finalSlotIndex + ": " + slots[finalSlotIndex] + " balls");
	
	                // Refresh slot labels after the last ball is dropped
	                if (ballIndex == ballCount - 1) {
	                    updateSlotLabels();
	                }
	            });
	
	            pathTransition.play();
	        });
	
	        delay.play();
	    }
	}
	
}


package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.mockTS1 = Mockito.mock(TorpedoStore.class);
    this.mockTS2 = Mockito.mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTS1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, secondResult);
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_WhenFirstIsEmpty(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_TwiceWhenFirstIsEmpty(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, secondResult);
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_WhenBothAreEmpty(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_TwiceWhenSecondIsEmpty(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, secondResult);
    verify(mockTS1, times(2)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_TwiceWhenSecondIsEmptyAndFirstHasOneOnly(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    when(mockTS1.isEmpty()).thenReturn(true);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(false, secondResult);
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_WhenBothEmpty(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }
}

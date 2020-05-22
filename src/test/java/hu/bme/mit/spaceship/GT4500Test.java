package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class GT4500Test {

  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;
  private GT4500 ship;

  @BeforeEach
  public void init(){

    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);

  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Primary_Store_Empty(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Secondary_Store_Empty(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Both_Stores_Empty(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Both_Stores_Loaded(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Primary_Store_Empty(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Stores_Loaded(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(0)).isEmpty();
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Stores_Empty(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Secondary_Store_Empty(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(0)).isEmpty();
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void changeTorpedo_toBeFired(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(primaryTorpedoStore, times(2)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(2)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Secondary_Store_Empty_Primary_Was_Used_Last(){
    //Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);
    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(primaryTorpedoStore, times(2)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
    verify(primaryTorpedoStore, times(2)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }
}


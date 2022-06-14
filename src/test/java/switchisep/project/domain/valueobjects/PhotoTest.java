package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @Test
    void testPhotoURLObjectConstruction_ValidState(){

        //Arrange
        String photoURL = "https://www.isep.ipp.pt/img/logo3.png";

        //Act
        Photo photo = Photo.createPhoto(photoURL);

        //Assert
        //Does not throw exceptions
        assertEquals(photo.getPhotoURL(), photoURL);
    }

    @Test
    void testPhotoURLObjectConstruction_InvalidState_IncorrectFormat(){

        //Arrange
        String photoURL = "IncorrectFormatForURL";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Photo.createPhoto(photoURL);
        });
    }

    @Test
    void testPhotoURLObjectConstruction_InvalidState_EmptyString(){

        //Arrange
        String photoURL = "";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Photo.createPhoto(photoURL);
        });
    }

    @Test
    void testPhotoURLObjectConstruction_InvalidState_EmptySpacedString(){

        //Arrange
        String photoURL = "     ";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Photo.createPhoto(photoURL);
        });
    }

    @Test
    void testPhotoURLObjectConstruction_InvalidState_Null(){

        //Arrange
        String photoURL = null;

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Photo.createPhoto(photoURL);
        });
    }
    //----------------------------------------------------------------------------------------------------------------//
    //Tests for equals and hashCode methods

    @Test
    void testSamValueAs_Null(){

        //Arrange
        String photoURL = "https://www.isep.ipp.pt/img/logo3.png";
        Photo photo = Photo.createPhoto(photoURL);

        //Act
        boolean result = photo.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsForSameObject(){

        //Arrange
        String photoURL = "https://www.isep.ipp.pt/img/logo3.png";

        //Act
        Photo photo = Photo.createPhoto(photoURL);

        //Assert
        assertEquals(photo,photo);
    }

    @Test
    void testEqualsForEqualObject(){

        //Arrange
        String photoURL1 = "https://www.isep.ipp.pt/img/logo3.png";
        String photoURL2 = "https://www.isep.ipp.pt/img/logo3.png";

        //Act
        Photo photo1 = Photo.createPhoto(photoURL1);
        Photo photo2 = Photo.createPhoto(photoURL2);

        //Assert
        assertEquals(photo1,photo2);
    }

    @Test
    void testEqualsForDifferentObject_DifferentClass(){

        //Arrange
        String photoURL1 = "https://www.isep.ipp.pt/img/logo3.png";

        //Act
        Photo photo1 = Photo.createPhoto(photoURL1);
        UserName userName = UserName.createUserName("User Name");

        //Assert
        assertNotEquals(photo1,userName);
    }

    @Test
    void testEqualsForDifferentObject_SameClass(){

        //Arrange
        String photoURL1 = "https://www.isep.ipp.pt/img/logo3.png";
        String photoURL2 = "https://www.isep.ipp.pt/img/logo4.png";

        //Act
        Photo photo1 = Photo.createPhoto(photoURL1);
        Photo photo2 = Photo.createPhoto(photoURL2);

        //Assert
        assertNotEquals(photo1,photo2);
    }

    @Test
    void testHashCodeForDifferentObject_SameClass(){

        //Arrange
        String photoURL1 = "https://www.isep.ipp.pt/img/logo3.png";
        String photoURL2 = "https://www.isep.ipp.pt/img/logo4.png";

        //Act
        Photo photo1 = Photo.createPhoto(photoURL1);
        Photo photo2 = Photo.createPhoto(photoURL2);

        //Assert
        assertNotEquals(photo1.hashCode(),photo2.hashCode());

    }

    @Test
    void testHashCodeForSameObject(){

        //Arrange
        String photoURL1 = "https://www.isep.ipp.pt/img/logo3.png";

        //Act
        Photo photo1 = Photo.createPhoto(photoURL1);

        //Assert
        assertEquals(photo1.hashCode(),photo1.hashCode());

    }
}
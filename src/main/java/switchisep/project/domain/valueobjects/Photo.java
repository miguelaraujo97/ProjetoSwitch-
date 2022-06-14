package switchisep.project.domain.valueobjects;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class Photo implements ValueObject<Photo>{

    private  String photoURL;

    private Photo(String photoURL){
        this.photoURL = photoURL;
    }

    public Photo() {
    }

    public static Photo createPhoto(String photoURL){
        if(photoURL == null || photoURL.isEmpty() || photoURL.isBlank()){
            throw new IllegalArgumentException("Photo URL cannot be empty or null");
        }
        else if(!isValidURL(photoURL)) {
            throw new IllegalArgumentException("URL invalid");
        }
        return new Photo(photoURL);
    }

    /**
     * Method to validate URL using regular expression
     *
     * @param url
     * @return true if valid
     */
    private static boolean isValidURL(String url) {
        boolean isValid = false;

        // Regex to check valid URL
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern p = Pattern.compile(regex);


        Matcher m = p.matcher(url);

        return m.matches();
    }

    public String getPhotoURL() {
        return photoURL;
    }

    @Override
    public boolean sameValueAs(Photo other) {
        return other != null && this.getPhotoURL().equals(
                other.getPhotoURL());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo other = (Photo) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoURL);
    }
}

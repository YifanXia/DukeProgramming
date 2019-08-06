package Course1;

import edu.duke.URLResource;

public class UrlFinder {

    private String webPage;
    private URLResource urlResource;

    public UrlFinder(String webPage) {
        this.webPage = webPage;
        this.urlResource = new URLResource(webPage);
    }

    public String matchUrl(String word) {
        String start = "href=\"";
        String end = "\"";
        int startIndex = word.indexOf(start);
        int endIndex = word.indexOf(end, startIndex + start.length());
        String url = word.substring(startIndex, endIndex + end.length());
        return url.substring(start.length(), url.length() - end.length());
    }

    public void printUrls(String keyword) {
        for (String word: urlResource.words()) {
            if (word.toLowerCase().contains(keyword)) {
                String matchedUrl = matchUrl(word);
                System.out.println(matchedUrl);
            }
        }
    }

    public static void main(String[] args) {
        UrlFinder urlFinder = new UrlFinder("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        urlFinder.printUrls("youtube.com");
    }

}

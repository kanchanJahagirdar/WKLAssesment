package businessComponent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;



/**
 * 
 * @author Kanchan Jahagirdar
 *
 */
public class Element {

	protected WebElement webElement;
	protected SeleniumHelper selenumhelper;

	
	public Element(SeleniumHelper selenumhelper, WebElement webElement) {
		this.webElement = webElement;
		this.selenumhelper = selenumhelper;
	}

	
	public Element setText(String text) {

		if (text!= null && (!"".equalsIgnoreCase(text.trim()))) {
			this.webElement.clear();
			this.webElement.sendKeys(text);
		}
		return this;
	}
	
	/**
	 * Use this method to simulate typing into an element, which may set its value.
	 * @param keys  character sequence to send to the element
	 */
	public void sendKeys(CharSequence... keys) {
		if(keys!=null){
			this.webElement.sendKeys(keys);
		}
	}

	
	public String getText() {
		return this.webElement.getText();
	}

	/**
	 * use to click on the element.
	 */
	public void click() {
		this.webElement.click();
	}
	
	public void sumbit() {
		try{
		this.webElement.submit();
		}
		catch(NoSuchElementException ex){
			//throw new NotFoundException(ex.getMessage());
		}
	}

	
	public void clear() {
		this.webElement.clear();
	}

	
	public String getTagName() {
		return this.webElement.getTagName();
	}

	
	public boolean isEnabled(){
		return this.webElement.isEnabled();
	}
	
	
	/**
	 * Find the first Element using the given method. This method is affected by the 'implicit wait' times in force at the time of execution. 
	 * @param elementSelector elementSelector - com.tieto.pcms.taf.web.ElementSelector class object with location mechanism.
	 * @return Element
	 * @throws Exception 
	 * @throws com.tieto.pcms.taf.asserting.NotFoundException
	 */
	public Element findElement(elementSelector selectMe) throws Exception{
		try {
			Element element= new Element(this.selenumhelper, this.webElement.findElement(selectMe.selectBy));
			return element;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			throw new Exception();// enception(String.format("Element %s not found on page %s", selectMe.getDescription(), browser.getDescription()));
		}
		
	}
	
	/**
	 * Find all elements within the current page using the given mechanism. 
	 * This method is affected by the 'implicit wait' times in force at the time of execution. 
	 * When implicitly waiting, this method will return as soon as there are more than 0 items in the found collection,
	 * or will return an empty list if the timeout is reached.
	 * @param elementSelector - com.tieto.pcms.taf.web.ElementSelector class object with location mechanism.
	 * @return A list of all Elements, or an empty list if nothing matches
	 */
	public List<Element> findElements(elementSelector selectMe)
	{
		List<WebElement> elements= this.webElement.findElements(selectMe.selectBy);
		List<Element> newElements=new ArrayList<Element>();
		for(WebElement ele:elements)
		{
			newElements.add(new Element(this.selenumhelper, ele));
		}
		return newElements;
	}
	
	public boolean isDisplayed(){
		return this.webElement.isDisplayed();
	}

	
	
	
}

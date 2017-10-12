package code;


/*
 * This class defines the Pyramid objects and it's variables
 */
public class Pyramid {
	
	private int _size;
	
	public Pyramid(){
		_size = 0;
	}
	
	//Increases the size of the pyramid
	public void incPyramid(){
		_size = _size + 1;
		System.out.println("Pyramid size is now " + _size);
	}
	
	//Accesor method for pyramid size
	public int getSize(){
		//System.out.println("Pyrmaid size is " + _size);
		return _size;
	}

}

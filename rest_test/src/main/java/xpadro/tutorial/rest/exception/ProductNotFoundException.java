package xpadro.tutorial.rest.exception;

/**
 * A runtime exception that arises when a warehouse doesn't contain a specified product
 * @author xpadro
 *
 */
public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4372776309073614775L;

	public ProductNotFoundException(String productId) {
		super(productId);
	}
}

package vn.hoidanit.laptopshop.service.Specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecs {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    // case 1 : Lấy ra tất cả sản phẩm có giá cả tối thiểu là min-price (vnd)
    public static Specification<Product> minPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(Product_.PRICE), price);
    }

    // case 2:Lấy ra tất cả sản phẩm có giá cả tối đa là 100000 (vnd)
    public static Specification<Product> maxPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get(Product_.PRICE), price);
    }

    // case3: Lấy ra tất cả sản phẩm có hãng sản xuất = APPLE
    public static Specification<Product> matchFactory(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.FACTORY), factory);
    }

    // case4:ấy ra tất cả sản phẩm có hãng sản xuất = APPLE hoặc DELL . Truyền nhiều
    // điều kiện,
    // ngăn cách các giá trị bởi dấu phẩy (điều kiện IN)
    public static Specification<Product> matchListFactory(List<String> factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factory);
    }

    // case5 lấy ra tất cả sản phẩm theo range (khoảng giá). 10 triệu <= price <= 15
    // triệu
    public static Specification<Product> matchPrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.gt(root.get(Product_.PRICE), min),
                criteriaBuilder.le(root.get(Product_.PRICE), max));
    }

    // case6 Lấy ra tất cả sản phẩm theo range (khoảng giá). 10 triệu <= price <= 15
    // triệu và 16 triệu <= price <= 20 triệu
    public static Specification<Product> matchMultiplePrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(Product_.PRICE), min, max);
    }

}

package store.mybooks.front.admin.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.mybooks.front.admin.book.service.BookAdminService;
import store.mybooks.front.admin.category.service.CategoryAdminService;
import store.mybooks.front.admin.coupon.model.request.CouponCreateRequest;
import store.mybooks.front.admin.coupon.model.response.CouponGetResponse;
import store.mybooks.front.admin.coupon.service.CouponService;
import store.mybooks.front.pageable.dto.response.PageResponse;

/**
 * packageName    : store.mybooks.front.admin.coupon
 * fileName       : CouponController
 * author         : damho-lee
 * date           : 2/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/29/24          damho-lee          최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/coupon")
public class CouponController {
    private final CouponService couponService;
    private final BookAdminService bookAdminService;
    private final CategoryAdminService categoryAdminService;

    /**
     * methodName : getCouponPage <br>
     * author : damho-lee <br>
     * description : 쿠폰 페이지 출력.<br>
     *
     * @param pageable Pageable
     * @param model    Model
     * @return string
     */
    @GetMapping
    public String getCouponPage(@PageableDefault Pageable pageable, Model model) {
        PageResponse<CouponGetResponse> response = couponService.getCoupons(pageable);
        model.addAttribute("coupons", response);
        return "admin/view/coupon/coupon";
    }

    /**
     * methodName : getCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 카테고리 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/register")
    public String getCouponRegisterPage() {
        return "admin/view/coupon/coupon-register";
    }

    /**
     * methodName : getTotalPercentageCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 전체 정률 할인 쿠폰 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/total-percentage-coupon-register")
    public String getTotalPercentageCouponRegisterPage() {
        return "admin/view/coupon/total-percentage-coupon-register";
    }

    /**
     * methodName : getFlatDiscountCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 전체 정액 할인 쿠폰 등록 페이지. <br>
     *
     * @return string
     */
    @GetMapping("/flat-discount-coupon-register")
    public String getFlatDiscountCouponRegisterPage() {
        return "admin/view/coupon/flat-discount-coupon-register";
    }

    /**
     * methodName : getBookPercentageCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 도서 정률 할인 쿠폰 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/book-percentage-coupon-register")
    public String getBookPercentageCouponRegisterPage(Model model) {
        model.addAttribute("books", bookAdminService.getBookForCoupon());
        return "admin/view/coupon/book-percentage-coupon-register";
    }

    /**
     * methodName : getBookFlatDiscountCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 도서 정액 할인 쿠폰 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/book-flat-discount-coupon-register")
    public String getBookFlatDiscountCouponRegisterPage(Model model) {
        model.addAttribute("books", bookAdminService.getBookForCoupon());
        return "admin/view/coupon/book-flat-discount-coupon-register";
    }

    /**
     * methodName : getCategoryPercentageCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 카테고리 정률 할인 쿠폰 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/category-percentage-coupon-register")
    public String getCategoryPercentageCouponRegisterPage(Model model) {
        model.addAttribute("categories", categoryAdminService.getCategories());
        return "admin/view/coupon/category-percentage-coupon-register";
    }

    /**
     * methodName : getCategoryFlatDiscountCouponRegisterPage <br>
     * author : damho-lee <br>
     * description : 카테고리 정액 할인 쿠폰 등록 페이지.<br>
     *
     * @return string
     */
    @GetMapping("/category-flat-discount-coupon-register")
    public String getCategoryFlatDiscountCouponRegisterPage(Model model) {
        model.addAttribute("categories", categoryAdminService.getCategories());
        return "admin/view/coupon/category-flat-discount-coupon-register";
    }

    /**
     * methodName : createTotalPercentageCoupon <br>
     * author : damho-lee <br>
     * description : 쿠폰 생성. <br>
     *
     * @param createRequest CouponCreateRequest
     * @return string
     */
    @PostMapping
    public String createTotalPercentageCoupon(@ModelAttribute CouponCreateRequest createRequest) {
        couponService.createCoupon(createRequest);
        return "redirect:/admin/coupon/register";
    }

    @PostMapping("/delete/{couponId}")
    public String deleteCoupon(@PathVariable("couponId") Long id) {
        couponService.deleteCoupon(id);
        return "redirect:/admin/coupon";
    }
}

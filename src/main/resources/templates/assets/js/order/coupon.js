function updateWrapValue(radio) {
    const wrapBtn = document.getElementById('coupon-btn');
    const wrapCost = radio.parentElement.querySelector('.discount-price').innerText;
    wrapBtn.setAttribute('coupon-name', radio.value);
    wrapBtn.setAttribute('coupon-cost', wrapCost);

}

function sendCoupon() {
    const couponId = document.getElementById('coupon-btn').getAttribute('coupon-name');
    const couponCost = document.getElementById('coupon-btn').getAttribute('coupon-cost');
    opener.document.getElementsByClassName("select-wrap").value = couponId
    opener.document.getElementById("coupon-name").textContent = couponId
    opener.document.getElementById("discount-cost").textContent = couponCost
    opener.document.getElementById("discount-cost").value = couponCost
    window.close()
}
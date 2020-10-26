-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 26, 2020 lúc 04:09 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `gymshop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `productName` varchar(200) NOT NULL,
  `productPrice` int(15) NOT NULL,
  `productImage` varchar(200) NOT NULL,
  `productWeight` float NOT NULL,
  `productColor` varchar(100) NOT NULL,
  `productTaste` varchar(100) NOT NULL,
  `productDesc` varchar(10000) NOT NULL,
  `typeId` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`productId`, `productName`, `productPrice`, `productImage`, `productWeight`, `productColor`, `productTaste`, `productDesc`, `typeId`) VALUES
(1, 'Whey Gold Standard', 790000, 'https://www.wheystore.vn/upload/product_optimize/image/56_image_whey_gold_standard_2lbs__900g__image_1587722707.jpg', 0.9, 'Đỏ', 'French Vanilla Creme', 'Whey Gold Standard 2Lbs (900G) đem lại cho bạn lượng Whey protein thuần túy, không pha trộn, đem đến cho bạn việc phát triển cơ bắp một cách hiệu quả nhất cho quá trình tập luyện!\r\nWhey Gold giúp đẩy nhanh khả năng hấp thụ protein cũng như là cắt giảm lượng mỡ và Carnibolic giúp cơ thể bạn chuyển hóa mỡ nhanh hơn . Bạn sẽ không phải lo lắng về vấn đề mỡ dư thừa nữa .', 1),
(2, 'Mass Fusion 12lbs 5.4kg', 1590000, 'https://www.wheystore.vn/upload/product_optimize/image/85_image_mass_fusion_12lbs_5_4kg__phien_ban_moi__image_1587722737.jpg', 5.4, '', 'Chocolate', 'Mass Fusion xứng đáng là dòng Mass CHẤT nhất thị trường hiện nay, phù hợp với người gầy muốn tăng cân với nguồn năng lượng sạch để duy trì cân nặng và hỗ trợ tăng cơ rất tốt.', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `producttype`
--

CREATE TABLE `producttype` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(200) NOT NULL,
  `typeImage` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `producttype`
--

INSERT INTO `producttype` (`typeId`, `typeName`, `typeImage`) VALUES
(1, 'Whey Protein', 'https://image.flaticon.com/icons/png/512/2365/2365686.png'),
(2, 'Sữa tăng cân', 'https://cdn2.iconfinder.com/data/icons/health-21/64/whey-protein-supplyments-gym-bodybuilder-muscle-512.png');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`);

--
-- Chỉ mục cho bảng `producttype`
--
ALTER TABLE `producttype`
  ADD PRIMARY KEY (`typeId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `producttype`
--
ALTER TABLE `producttype`
  MODIFY `typeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Май 20 2018 г., 02:54
-- Версия сервера: 5.7.21
-- Версия PHP: 7.1.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `shop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `dcategory`
--

CREATE TABLE `dcategory` (
  `id` int(11) NOT NULL,
  `name` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `dcategory`
--

INSERT INTO `dcategory` (`id`, `name`) VALUES
(1, 'ұялы телефондар'),
(2, 'Периферия'),
(3, 'Теледидарлар'),
(4, '1'),
(5, '2'),
(6, '3');

-- --------------------------------------------------------

--
-- Структура таблицы `groupmembers`
--

CREATE TABLE `groupmembers` (
  `id` varchar(50) NOT NULL,
  `g_name` varchar(200) NOT NULL,
  `g_member` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groupmembers`
--

INSERT INTO `groupmembers` (`id`, `g_name`, `g_member`) VALUES
('1', 'EMAKET_ADMIN', 'ADMIN'),
('2', 'EMAKET_USER', 'user');

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `g_name` varchar(200) NOT NULL,
  `g_description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`g_name`, `g_description`) VALUES
('EMAKET_ADMIN', 'EMAKET_ADMIN'),
('EMAKET_ADMIN', 'EMAKET_ADMIN'),
('EMAKET_USER', 'EMAKET_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `t_data`
--

CREATE TABLE `t_data` (
  `id` varchar(222) NOT NULL,
  `descr` text NOT NULL,
  `cost` decimal(10,0) NOT NULL,
  `count` int(11) NOT NULL,
  `t_name` varchar(222) NOT NULL,
  `img_id` varchar(222) NOT NULL,
  `cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `t_image`
--

CREATE TABLE `t_image` (
  `t_id` varchar(222) NOT NULL,
  `fileB` blob NOT NULL,
  `file_name` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `emp_id` int(11) NOT NULL,
  `u_name` varchar(200) NOT NULL,
  `u_password` varchar(90) DEFAULT NULL,
  `u_description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`emp_id`, `u_name`, `u_password`, `u_description`) VALUES
(1, 'ADMIN', '$2a$10$B02yyzKBsIx2Uo3/W791nOieWcMqQFDWOkok5lXkXUlBEcUs3Dq7O', NULL),
(2, 'user', '$2a$10$wmrBytbGfmlpdOMWvX0I2Oi64rwsRX5/EAVlicRGWM1BS8MTQayYK', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `user_detail`
--

CREATE TABLE `user_detail` (
  `id` varchar(222) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `iin` varchar(12) DEFAULT NULL,
  `lastname` varchar(200) DEFAULT NULL,
  `firstname` varchar(200) DEFAULT NULL,
  `middlename` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_detail`
--

INSERT INTO `user_detail` (`id`, `emp_id`, `iin`, `lastname`, `firstname`, `middlename`) VALUES
('1', 1, '120419400123', 'УДАЙБЕРГЕН', 'ЕКЖАН', 'ЖАНОВИЧ'),
('2', 2, '950623351543', 'Иванов', 'Иван', 'Иванович');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `dcategory`
--
ALTER TABLE `dcategory`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`emp_id`);

--
-- Индексы таблицы `user_detail`
--
ALTER TABLE `user_detail`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `dcategory`
--
ALTER TABLE `dcategory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
